package com.example.bk_recp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private int password;

    @Column(name="email")
    private String email;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //Каскадное обновление, EAGER - подгружение связанных сущностей
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Note> notes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Recipes> recipes;

    public User() {

    }
}
