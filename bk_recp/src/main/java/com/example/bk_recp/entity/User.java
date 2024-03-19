package com.example.bk_recp.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    @Column(name="login", unique = true)
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="name_user")
    private String name_user;

    @Column(name="date_reg")
    private Date date_reg;

    @ManyToOne(cascade = CascadeType.REFRESH) //Каскадное обновление, EAGER - подгружение связанных сущностей
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<Note> notes;

    @OneToMany(mappedBy = "user")
    private List<Recipes> recipes;

    @OneToMany(mappedBy = "user")
    private List<Review> review;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //Тип активности

        return true;
    }

    public String info() {
        return "" + getLogin() +
                ", " + getPassword() + ", " +
                getEmail() + ", " + getUserType();
    }
}
