package com.example.bk_recp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name="recipes")
@Data
@AllArgsConstructor
public class Recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_recipe;

    @Column(name="title_recipe")
    private String title_recipe;

    @Column(name="description", columnDefinition = "text")
    private String description;

    @Column(name="сompound", columnDefinition = "text")
    private String compound;

    @Lob
    @Column(name="photo_recipe")
    private byte[] photo_recipe;

    @Column(name="view_us")
    private int view_us;

    @Column(name="date_reg")
    private Date date_reg;

    @Column(name="date_upd")
    private Date date_upd;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public Recipes() {

    }
}
