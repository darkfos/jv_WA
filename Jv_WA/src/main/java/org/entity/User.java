package org.entity;

import org.hibernate.type.DateType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class User {

    @Id
    @Column(name="id_user")
    private int id_user;

    @Column(name="login")
    private String login;

    @Column(name="email")
    private String email;

    @Column(name="date_reg")
    private DateType date_reg;

    @Column(name="phone")
    private int phone;

    @OneToMany
    @JoinColumn(name="id_us_tp")
    private UserType userType;

    public User() {
    }

    public User(String login, String email, DateType date_reg,
                int phone, UserType userType) {

        this.login = login;
        this.email = email;
        this.date_reg = date_reg;
        this.phone = phone;
        this.userType = userType;
    }


    @Override
    public String toString() {
        return "User{" +
                "id_user=" + this.id_user +
                ", login=" + this.login +
                ", email=" + this.email +
                ", date_reg=" + this.date_reg +
                ", phone=" + this.phone +
                ", id_user_type" + this.userType +
                "}";
    }


    //Get and set attributes

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

    public void setDate_reg(DateType date_reg) {
        this.date_reg = date_reg;
    }

    public DateType getDate_reg() {
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

    public UserType getId_user() {
        return this.userType;
    }
}
