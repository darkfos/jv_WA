package com.example.demo.models;


import org.hibernate.type.DateType;

import javax.persistence.*;

@Entity
@Table(name="Book_recipes")
public class Recipe {

    @Id
    @Column(name="id_recipe")
    private Long id_recipe;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="sostav")
    private String sostav;

    @Column(name="photo")
    private byte photo;

    @Column(name="date_reg")
    private DateType date_reg;

    @Column(name="date_upd")
    private DateType date_upd;

    @Column(name="date_reg")
    private int view_us;

    @OneToMany
    @JoinColumn(name="id_user")
    private User user;


    public Recipe() {
    }

    public Recipe(String title, String description, String sostav, DateType date_reg,
                  DateType date_upd, int view_us, User user) {

        this.title = title;
        this.description = description;
        this.sostav = sostav;
        this.date_reg = date_reg;
        this.date_upd = date_upd;
        this.view_us = view_us;
        this.user = user;
    }

    public Recipe(String title, String description, String sostav, DateType date_reg,
             int view_us, User user) {

        this.title = title;
        this.description = description;
        this.sostav = sostav;
        this.date_reg = date_reg;
        this.date_upd = this.date_reg;
        this.view_us = view_us;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id_recipe=" + this.id_recipe +
                ", title=" + this.title +
                ", description=" + this.description +
                ", sostav=" + this.sostav +
                ", date_reg=" + this.date_reg +
                ", date_upd=" + this.date_upd +
                ", view_us=" + this.view_us +
                ", user=" + this.user +
                "}";
    }

    //Get and set attributes

    public void setId_recipe(Long id_recipe) {
        this.id_recipe = id_recipe;
    }

    public Long getId_recipe() {
        return this.id_recipe;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setSostav(String sostav) {
        this.sostav = sostav;
    }

    public String getSostav() {
        return this.sostav;
    }

    public void setDate_reg(DateType date_reg) {
        this.date_reg = date_reg;
    }

    public DateType getDate_reg() {
        return this.date_reg;
    }

    public void setDate_upd(DateType date_upd) {
        this.date_upd = date_upd;
    }

    public DateType getDate_upd() {
        return this.date_upd;
    }

    public void setView_us(int view_us) {
        this.view_us = view_us;
    }

    public int getView_us() {
        return this.view_us;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}

