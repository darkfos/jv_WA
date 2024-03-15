package com.example.bk_recp.controllers;

import com.example.bk_recp.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;


    @GetMapping("/note")
    public String notes(Model model) {
        model.addAttribute("title_page", "Заметки");
        model.addAttribute("all_notes", noteService.getNotes());
        return "notes";
    }

    @GetMapping("/create_note")
    public String create_note(Model model) {
        model.addAttribute("title_page", "Создание заметки");
        return "create_note";
    }
}
