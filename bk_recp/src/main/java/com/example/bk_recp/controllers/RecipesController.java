package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.services.NoteService;
import com.example.bk_recp.services.RecipesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class RecipesController {

    //CRUD operations to recipes
    private final RecipesService recipesService;
    private final NoteService noteService;


    /**
     * Отображение списка рецептов рецептов
     * @param model
     * @return
     */
    @GetMapping("/recipes")
    public String get_recipes(Model model, Principal principal) {
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        model.addAttribute("recipes", recipesService.get_recipes(principal));
        return "recipes";
    }

    /**
     * Отображение уникального рецепта
     * @param recipe_id
     * @param model
     * @return
     */
    @GetMapping("/recipe_unique")
    public String get_recipe_by_id(@RequestParam("recipe_id") Long recipe_id, Model model, Principal principal) throws IOException {
        Recipes recipe = recipesService.getRecipeById(recipe_id);

        //Photo coder
        recipe.setPhoto(
                Base64.getEncoder().encodeToString(recipe.getPhoto_recipe())
        );
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        model.addAttribute("recipe", recipe);
        return "recipe_info";
    }

    /**
     * Создание рецепта
     * @param model
     * @return
     */
    @GetMapping("/create_recipe")
    public String create_recipe(Model model, Principal principal) {
        model.addAttribute("title_page", "Создание рецепта");
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "create_recipe";
    }


    /**
     * Создание рецепта
     * @param main_file
     * @param new_recipe
     * @return
     */
    @PostMapping("/create_new_recipe")
    public String create_new_recipe(@RequestParam("main_file") MultipartFile main_file, Recipes new_recipe, Model model, Principal principal) throws IOException {

        //Date
        LocalDate localdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date lc_date = Date.valueOf(localdate.format(formatter));

        //Create date
        new_recipe.setDate_reg(lc_date);
        new_recipe.setDate_upd(lc_date);

        recipesService.addRecipe(new_recipe, main_file, principal);
        model.addAttribute("user", principal);
        return "redirect:/";
    }

    /**
     * Удаление рецепта по id
     * @param id_recipe
     * @return
     */
    @PostMapping("/delete_recipe")
    public String delete_recipe_by_id(@RequestParam("id_recipe") Long id_recipe, Model model, Principal principal) {
        recipesService.delRecipeById(id_recipe);
        model.addAttribute("user", principal);
        return "redirect:/";
    }
}
