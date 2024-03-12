package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NotesController {

    @GetMapping("/notes")
    public String notes(Model model) {
        model.addAttribute("title_page", "Мои заметки");
        return "notes";
    }
}
