package com.example.bk_recp.services;

import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.repositories.RecipesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipesService {

    private final RecipesRepository recipesRepository;

    /**
     * Возвращаем все записи рецептов
     * @return
     */
    public List<Recipes> get_recipes() {

        List<Recipes> recipes = recipesRepository.findAll();
        log.info("Request get all recipes");

        //Code Image
        for (Recipes recipe: recipes) {
            String img = Base64.getEncoder().encodeToString(recipe.getPhoto_recipe());
            recipe.setPhoto(img);
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
    public void delRecipeById(Long id_recipe) {

        recipesRepository.deleteById(id_recipe);
        log.info("Request to del recipe");
    }


    /**
     * Добавляем запись recipe по id
     * @param new_recipe
     */
    public void addRecipe(Recipes new_recipe, MultipartFile mainImage) {

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
        }
        recipesRepository.save(new_recipe);
        log.info("Request to add new recipe");
    }
}
