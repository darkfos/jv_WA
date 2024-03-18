package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.Note;
import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.entity.Review;
import com.example.bk_recp.services.NoteService;
import com.example.bk_recp.services.ReviewServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewServices reviewServices;
    private final NoteService noteService;

    /**
     * Открытие страницы создания отзыва
     * @return
     */
    @GetMapping("/create_review")
    public String create_recipe(Model model, Principal principal) {
        model.addAttribute("title_page", "Создание отзыва");
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "create_review";
    }

    /**
     * Открытие страницы со всеми отзывами
     * @return
     */
    @GetMapping("/reviews")
    public String all_recipe(Model model, Principal principal) {
        model.addAttribute("title_page", "Отзывы");
        model.addAttribute("reviews", reviewServices.get_reviews());
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "all_review";
    }

    @PostMapping("/create_review/create")
    public String create_new_recipe(Review review, Model model, Principal principal) {
        reviewServices.add_review(review, principal);
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "redirect:/";
    }
}
