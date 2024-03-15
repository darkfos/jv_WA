package com.example.bk_recp.services;

import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.repositories.RecipesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipesController {

    private final RecipesRepository recipesRepository;

    /**
     * Возвращаем все записи рецептов
     * @return
     */
    public List<Recipes> get_recipes() {

        List<Recipes> recipes = recipesRepository.findAll();
        log.info("Request get all recipes");
        return recipes;
    }

    /**
     * Возвращаем запись recipe по id
     * @param id_recipe
     * @return
     */
    public Recipes getRecipeById(Long id_recipe) {

        Recipes recipe = recipesRepository.findById(id_recipe).orElse(null);
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
    public void addRecipe(Recipes new_recipe) {

        recipesRepository.save(new_recipe);
        log.info("Request to add new recipe");
    }
}
