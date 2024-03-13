package com.example.bk_recp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NotesController {

    @GetMapping("/notes")
    public String notes(Model model) {
        model.addAttribute("title_page", "Мои заметки");
        return "notes";
    }
}