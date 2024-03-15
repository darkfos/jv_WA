package com.example.bk_recp.services;

import com.example.bk_recp.entity.Note;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    //Объект модели
    private List<Note> notes = new ArrayList<>();
    private Long new_id = 1L;
    {
        notes.add(new Note(new_id++, "Новая заметка 1", "Её крутое описание",
                Date.valueOf("2023-10-04")));
        notes.add(new Note(new_id++, "Новая заметка 2", "Её крутое описание #2",
                Date.valueOf("2023-10-04")));
    }


    public List<Note> getNotes() {
        //Получение всех заметок
        return notes;
    }

    public void add_note(Note new_note) {
        new_note.setId_notes(new_id++);
        notes.add(new_note);
    }

    public void del_note(Long id_note) {
        notes.removeIf(
                note -> note.getId_notes().equals(id_note));
    }

    public Note getNoteById(Long id_note) {
        //Получаем Note по Id

        for (Note note : notes) {
            if (note.getId_notes().equals(id_note)) {
                return note;
            }
        }
        return null;
    }
}
