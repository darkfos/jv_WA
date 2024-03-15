package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.services.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class RecipesController {

    //CRUD operations to recipes
    private final RecipesService recipesService;


    /**
     * Отображение списка рецептов рецептов
     * @param model
     * @return
     */
    @GetMapping("/recipes")
    public String get_recipes(Model model) {
        model.addAttribute("recipes", recipesService.get_recipes());
        return "recipes";
    }

    /**
     * Отображение уникального рецепта
     * @param recipe_id
     * @param model
     * @return
     */
    @GetMapping("/recipe")
    public String get_recipe_by_id(@RequestParam("recipe_id") Long recipe_id, Model model) {
        Recipes recipe = recipesService.getRecipeById(recipe_id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    /**
     * Создание рецепта
     * @param model
     * @return
     */
    @GetMapping("/create_recipe")
    public String create_recipe(Model model) {
        model.addAttribute("title_page", "Создание рецепта");
        return "create_recipes";
    }


    /**
     * Создание рецепта
     * @param main_file
     * @param new_recipe
     * @return
     */
    @PostMapping("/create_new_recipe")
    public String create_new_recipe(@RequestParam("main_file") MultipartFile main_file, Recipes new_recipe) {
        recipesService.addRecipe(new_recipe, main_file);
        return "redirect:/";
    }

    /**
     * Удаление рецепта по id
     * @param id_recipe
     * @return
     */
    @PostMapping("/delete_recipe")
    public String delete_recipe_by_id(@RequestParam("id_recipe") Long id_recipe) {
        recipesService.delRecipeById(id_recipe);
        return "redirect:/";
    }
}
