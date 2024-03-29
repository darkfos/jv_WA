package com.example.bk_recp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name="usertype")
public class UserType {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_user_type;

    @Column(name="name_type")
    private String name_type;

    @Column(name="code_type")
    private int code_type;


    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL) //Каскадное обновление, EAGER - подгружение связанных сущностей
    private List<User> users;

    public UserType() {
    }
}
