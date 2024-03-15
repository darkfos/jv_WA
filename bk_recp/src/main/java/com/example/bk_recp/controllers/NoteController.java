package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.Note;
import com.example.bk_recp.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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

    @PostMapping("/note/{id_note}")
    public String get_note(@PathVariable Long id_note, Model model) {
        return "note_info";
    }

    @PostMapping("/create_note/create")
    public String create_new_note(Note new_note) {
        new_note.setId_notes(7L);
        Date date_now = new Date();
        new_note.setDate_cr(date_now);
        noteService.add_note(new_note);
        return "redirect:/";
    }

    @PostMapping("/delete_note")
    public String delete_note(@RequestParam("id_note") Long id_note) {
        noteService.del_note(id_note);
        return "redirect:/";
    }
}
