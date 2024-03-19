package com.example.bk_recp.services;

import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.entity.User;
import com.example.bk_recp.repositories.RecipesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipesService {

    private final RecipesRepository recipesRepository;
    private final NoteService noteService;

    /**
     * Возвращаем все записи рецептов
     * @return
     */
    public List<Recipes> get_recipes(Principal principal) {

        List<Recipes> recipes = recipesRepository.findAll();
        log.info("Request get all recipes");

        //Get recipe for user
        ArrayList<Recipes> result = new ArrayList<>();

        for (Recipes recipe : recipes) {
            if (recipe.getUser().equals(noteService.getUserByPrincipal(principal))) {
                result.add(recipe);
            }
        }

        //Code Image
        for (Recipes recipe: result) {
            String img = Base64.getEncoder().encodeToString(recipe.getPhoto_recipe());
            recipe.setPhoto(img);
        }


        return result;
    }

    /**
     * Возвращаем все записи рецептов
     * @return
     */
    public List<Recipes> get_recipes_random() {

        List<Recipes> recipes = recipesRepository.findAll();
        log.info("Request get all recipes");

        //Get recipe for user
        ArrayList<Recipes> result = new ArrayList<>();

        Random rnd = new Random();

        if (recipes.size() > 0) {
            int rec = rnd.nextInt(recipes.size());

            for (int i = 0; i < rec; i++) {
                result.add(
                        recipes.get(
                                rnd.nextInt(recipes.size())
                        )
                );
            }

            //Code Image
            for (Recipes recipe : result) {
                String img = Base64.getEncoder().encodeToString(recipe.getPhoto_recipe());
                recipe.setPhoto(img);
            }


            return result;
        }

        return recipes;
    }

    /**
     * Возвращаем запись recipe по id
     * @param id_recipe
     * @return
     */
    public Recipes getRecipeById(Long id_recipe) {

        Recipes recipe = recipesRepository.findById(id_recipe).orElse(null);

        //View
        recipe.setView_us(
                recipe.getView_us() + 1
        );

        recipesRepository.save(recipe);
        log.info("Request to get recipe by id");
        return recipe;
    }

    /**
     * Удаляем запись recipe по id
     * @param id_recipe
     */
    public void delRecipeById(Long id_recipe, User user) {

        Recipes recipes_to_del = recipesRepository.findById(id_recipe).orElse(null);

        if (recipes_to_del.getUser().equals(user)) {
            recipesRepository.deleteById(id_recipe);
            log.info("Request to del recipe");
        }
    }


    /**
     * Добавляем запись recipe по id
     * @param new_recipe
     */
    public boolean addRecipe(Recipes new_recipe, MultipartFile mainImage, Principal principal) {
        //Проверяем загрузили ли фото
        if (mainImage.getSize() != 0) {
            try {
                new_recipe.setPhoto_recipe(
                        mainImage.getBytes()
                );
            } catch (IOException e) {
                log.error("Dont to add recipe, error");
            }
        } else {
            log.info("Dont to add recipe, nope photo");
            return false;
        }
        new_recipe.setUser(noteService.getUserByPrincipal(principal));
        recipesRepository.save(new_recipe);
        log.info("Request to add new recipe");
        return true;
    }

    /**
     * Обновление рецепта
     * @param id_recipe
     * @param title_recipe
     * @param description
     * @param compound
     * @return
     */
    public boolean update_recipe(Long id_recipe,
                                 String title_recipe, String description,
                                 String compound, User user) {
        Recipes recipes = recipesRepository.findById(id_recipe).orElse(null);

        if (recipes != null && recipes.getUser().equals(user)) {
            recipes.setTitle_recipe(title_recipe);
            recipes.setDescription(description);
            recipes.setCompound(compound);

            //Date
            LocalDate localdate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date lc_date = Date.valueOf(localdate.format(formatter));

            recipes.setDate_upd(lc_date);
            recipesRepository.save(recipes);
            return true;
        } else { return false; }
    }
}
