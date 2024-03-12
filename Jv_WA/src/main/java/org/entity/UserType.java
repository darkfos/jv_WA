package org.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserType")
public class UserType {
    /*
        Entity - Сущность отображение в базе данных
        Table - К какой именно таблице привязываем класс
        Column - Связываем атрибуты с колонками
        id - Primary Key
    */

    @Id
    @Column(name="id_user_type")
    private int id_user_type;

    @Column(name="name_type")
    private String name_type;

    @Column(name="code_type")
    private int code_type;


    public UserType() {
    }

    public UserType(String name_type, int code_type) {
        this.name_type = name_type;
        this.code_type = code_type;
    }


    @Override
    public String toString() {
        return "UserType{" +
                "user_type_id=" + this.id_user_type +
                ", name_type=" + this.name_type +
                ", code_type=" + this.code_type;
    }


    //Get and Set attributes

    public void setId_user_type(int id_user_type) {
        this.id_user_type = id_user_type;
    }

    public int getId_user_type() {
        return this.id_user_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

    public String getName_type() {
        return this.name_type;
    }

    public void setCode_type(int code_type) {
        this.code_type = code_type;
    }

    public int getCode_type() {
        return this.code_type;
    }
}
