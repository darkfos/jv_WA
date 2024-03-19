package com.example.bk_recp.services;

import com.example.bk_recp.entity.Note;
import com.example.bk_recp.entity.User;
import com.example.bk_recp.repositories.NoteRepository;
import com.example.bk_recp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //Логи
public class NoteService {

    //Объект модели
    private final NoteRepository notes;

    private final UserRepository userRepository;


    /**
     * Список всех заметок
     * @return
     */
    public List<Note> getNotes(Principal pr) {
        //Получение всех заметок

        User user = getUserByPrincipal(pr);
        ArrayList<Note> all_notes = (ArrayList<Note>) notes.findAll();

        ArrayList<Note> result = new ArrayList<>();

        for (Note note : all_notes) {
            if (note.getUser().equals(user)) {
                result.add(note);
            }
        }

        log.info("Request to all notes");

        return result;
    }

    /**
     * Добавление заметки
     * @param pr
     * @param new_note
     */
    public void add_note(Principal pr, Note new_note) {
        //Добавляем заметку

        log.info("Request to save note");

        new_note.setUser(getUserByPrincipal(pr));
        notes.save(new_note);
    }


    /**
     * Находим юзера по principal
     * @param pr
     * @return
     */
    public User getUserByPrincipal(Principal pr) {
        if (pr == null) {
            return new User();
        }

        User user = userRepository.findByLogin(pr.getName());
        return user;
    }

    public void del_note(Long id_note) {
        //Удаляем запись о заметке через ID

        notes.deleteById(id_note);
    }

    public Note getNoteById(Long id_note) {
        //Получаем Note по Id
        Optional<Note> optionalNote = notes.findById(id_note);

        if (optionalNote.isPresent()) {
            Note unique_note = optionalNote.get();
            unique_note.setView(
                    unique_note.getView() + 1
            );
            notes.save(unique_note);
            return notes.findById(id_note).orElse(null);
        }
        else {
            return null;
        }
    }

    /**
     * Обновление note
     * @param id_note
     * @param title_note
     * @param description
     */
    public void update_note(Long id_note, String title_note, String description) {
        Note note = notes.findById(id_note).orElse(null);
        note.setTitle_note(title_note);
        note.setDescription(description);

        //Date
        LocalDate localdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date lc_date = Date.valueOf(localdate.format(formatter));
        note.setDate_upd(lc_date);
        notes.save(note);
    }
}
