package com.example.bk_recp.controllers;


import com.example.bk_recp.models.Note;
import com.example.bk_recp.models.User;
import com.example.bk_recp.repository.NoteRepository;
import com.example.bk_recp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class NotesController {

    @Autowired
    private NoteRepository note;

    @GetMapping("/notes")
    public String notes(Model model) {
        Iterable<Note> notes = note.findAll();
        model.addAttribute("title_page", "Мои заметки");
        model.addAttribute("notes", notes);
        return "notes";
    }


    @PostMapping("/create_note")
    public String add_note(Model model) {
        model.addAttribute("title_page", "Создание заметки");
        return "create_note";
    }
}
