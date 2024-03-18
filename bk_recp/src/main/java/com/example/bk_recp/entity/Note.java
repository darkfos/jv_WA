package com.example.bk_recp.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="notes")
@Data
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Тип генерации автоинкремент
    private Long id_notes;

    @Column(name="title_note", columnDefinition = "text") //Большой тип текста
    private String title_note;

    @Column(name="description")
    private String description;

    @Column(name="date_cr")
    private Date date_cr;

    @Column(name="view")
    private int view;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;

    public Note() {

    }
}
