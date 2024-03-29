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

import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;


    @GetMapping("/note")
    public String notes(Model model, Principal principal) {
        model.addAttribute("title_page", "Заметки");
        model.addAttribute("all_notes", noteService.getNotes(principal));
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "notes";
    }

    @GetMapping("/create_note")
    public String create_note(Model model, Principal principal) {
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        model.addAttribute("title_page", "Создание заметки");
        return "create_note";
    }

    @GetMapping("/note_by_id")
    public String get_note(@RequestParam("id_note") Long id_note, Model model, Principal principal) {
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        Note unique_note = noteService.getNoteById(id_note);
        model.addAttribute("note", unique_note);
        System.out.println(id_note);
        return "note_info";
    }

    @PostMapping("/create_note/create")
    public String create_new_note(Note new_note, Model model, Principal principal) throws ParseException {

        LocalDate localdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date lc_date = Date.valueOf(localdate.format(formatter));
        new_note.setDate_cr(lc_date);
        new_note.setDate_upd(lc_date);
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        noteService.add_note(principal, new_note);
        return "redirect:/";
    }

    @PostMapping("/delete_note")
    public String delete_note(@RequestParam("id_note") Long id_note, Model model, Principal principal) {
        noteService.del_note(id_note);
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "redirect:/";
    }


    @GetMapping("/update_note")
    public String update_recipe_page(@RequestParam("id_note") Long id_note, Model model, Principal principal) {
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        model.addAttribute("note", noteService.getNoteById(id_note));
        return "upd_note";
    }

    @PostMapping("/update_note")
    public String update_note(@RequestParam("title_note") String title_note, @RequestParam("description_text") String description,
                              @RequestParam("id_note") Long id_note, Model model, Principal principal) {
        noteService.update_note(id_note, title_note, description);
        return "redirect:/";
    }
}
