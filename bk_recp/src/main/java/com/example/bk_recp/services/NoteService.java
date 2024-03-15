package com.example.bk_recp.services;

import com.example.bk_recp.entity.Note;
import com.example.bk_recp.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //Логи
public class NoteService {

    //Объект модели
    private final NoteRepository notes;

    public List<Note> getNotes() {
        //Получение всех заметок

        ArrayList<Note> all_notes = (ArrayList<Note>) notes.findAll();
        log.info("Request to all notes");

        return all_notes;
    }

    public void add_note(Note new_note) {
        //Добавляем заметку

        log.info("Request to save note");
        notes.save(new_note);
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
}
