package com.example.bk_recp.controllers;

import com.example.bk_recp.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    //Приём HTTP request

    private final NoteService noteService;

    @GetMapping("/")
    public String main_page(Model model) {
        return "main_page";
    }

}
