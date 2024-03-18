package com.example.bk_recp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_review;

    @Column(name="name_user")
    private String name_user;

    @Column(name="message_user")
    private String message_user;

    @Column(name="email")
    private String email;

    @ManyToOne()
    private User user;

    public Review() {

    }
}
