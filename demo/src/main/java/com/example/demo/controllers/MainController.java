package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/main_page")
    public String main_page(Model model) {
        model.addAttribute("message", "помогите мне");
        model.addAttribute("title_page", "Основная страница");
        return "main_page";
    }

}