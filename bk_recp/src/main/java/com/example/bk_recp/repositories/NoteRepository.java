package com.example.bk_recp.repositories;

import com.example.bk_recp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    //Логика работы с таблицой, получаем доступ к методам SQL
}
