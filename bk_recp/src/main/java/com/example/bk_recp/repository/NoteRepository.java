package com.example.bk_recp.repository;

import com.example.bk_recp.controllers.NotesController;
import com.example.bk_recp.models.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
