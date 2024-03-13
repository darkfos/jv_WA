package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/main_page", params = {"name", "name"})
    public Model main_page(@RequestParam("name") String name, Model model) {
        model.addAttribute("message", "помогите мне");
        model.addAttribute("title_page", "Основная страница");
        model.addAttribute("name_user", name);
        return model;
    }

    @GetMapping("/main_page")
    public Model main_page(Model model) {
        model.addAttribute("message", "помогите мне");
        model.addAttribute("title_page", "Основная страница");
        System.out.print("MAIN Get");
        return model;
    }

}