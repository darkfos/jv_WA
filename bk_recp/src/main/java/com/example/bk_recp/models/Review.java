package com.example.bk_recp.models;


import javax.persistence.*;

@Entity
@Table(name="Reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_review")
    private Long id_review;

    @Column(name="name_user")
    private String name_user;

    @Column(name="message_user")
    private String message;

    @OneToOne
    @JoinColumn(name="id_usr")
    private User user;

    public Review() {
    }

    public Review(String name_user, String message, User user) {
        this.name_user = name_user;
        this.message = message;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Review{" +
                "id_review=" + this.id_review +
                ", name_user=" + this.name_user +
                ", message=" + this.message +
                ", id_user=" + this.user +
                "}";
    }


    //Get and set attributes

    public void setId_review(Long id_review) {
        this.id_review = id_review;
    }

    public Long getId_review() {
        return this.id_review;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getName_user() {
        return this.name_user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}