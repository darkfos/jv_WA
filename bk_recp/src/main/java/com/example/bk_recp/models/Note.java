package com.example.bk_recp.models;

import org.hibernate.type.DateType;

import javax.persistence.*;

@Entity
@Table(name="Notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_notes")
    private Long id_notes;

    @Column(name="title_note")
    private String title_notes;

    @Column(name="description")
    private String description;

    @Column(name="date_cr")
    private DateType date_reg;

    @OneToMany
    @JoinColumn(name="id_user")
    private User user;


    public Note() {
    }

    public Note(String title_notes, String description, DateType date_reg, User user) {
        this.title_notes = title_notes;
        this.description = description;
        this.date_reg = date_reg;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id_note=" + this.id_notes +
                ", title_note=" + this.title_notes +
                ", description=" + this.description +
                ", date_reg=" + this.date_reg +
                ", user=" + this.user +
                "}";
    }

    //Get and set attributes

    public void setId_notes(Long id_notes) {
        this.id_notes = id_notes;
    }

    public Long getId_notes() {
        return this.id_notes;
    }

    public void setTitle_notes(String title_notes) {
        this.title_notes = title_notes;
    }

    public String getTitle_notes() {
        return this.title_notes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDate_reg(DateType date_reg) {
        this.date_reg = date_reg;
    }

    public DateType getDate_reg() {
        return this.date_reg;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}