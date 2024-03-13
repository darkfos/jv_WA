package com.example.bk_recp.models;

import org.hibernate.type.DateType;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_user")
    private Long id_user;


    @Id
    @Column(name="name_user")
    private String name_user;

    @Column(name="login")
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="date_reg")
    private Date date_reg;

    @Column(name="phone")
    private int phone;

    @ManyToOne
    @JoinColumn(name="id_us_tp")
    private UserType userType;

    public User() {
    }

    public User(String login, String email, Date date_reg,
                int phone, UserType userType) {

        this.login = login;
        this.email = email;
        this.date_reg = date_reg;
        this.phone = phone;
        this.userType = userType;
        this.name_user = "Бобёр";
    }


    @Override
    public String toString() {
        return "User{" +
                "id_user=" + this.id_user +
                ", login=" + this.login +
                ", email=" + this.email +
                ", date_reg=" + this.date_reg +
                ", phone=" + this.phone +
                ", id_user_type=" + this.userType +
                ", username=" + this.name_user +
                "}";
    }


    //Get and set attributes

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public Long getId_user() {
        return this.id_user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setDate_reg(Date date_reg) {
        this.date_reg = date_reg;
    }

    public Date getDate_reg() {
        return this.date_reg;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return this.phone;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return this.userType;
    }
}