package com.example.bk_recp.controllers;

import com.example.bk_recp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class UsersController {

    private final static ArrayList<User> users = new ArrayList<>();


    /**
     * Добавляем пользователя
     * @param user
     * @return
     */
    @PostMapping("/user")
    public ArrayList<User> add_user(@RequestBody User user) {
        users.add(user);
        return users;
    }

    @GetMapping("/users")
    public ArrayList<User> get_users() {
        /*
            Получение всех пользователей
        */

        return users;
    }

    @GetMapping("/user/{userlogin}")
    public User getUserByUsername(@PathVariable("userlogin") String userlogin) {
        /*
            Получаем пользователя по логину
        */

        return users.stream().filter(user -> user.getLogin().equals(userlogin))
                .findFirst().get();
    }

    @PutMapping("/user/{userlogin}")
    public User update(@PathVariable("userlogin") String userlogin) {
        /*
            Обновление логина пользователя
        */

        users.stream().filter(user -> user.getLogin().equals(userlogin))
                .findAny();

        return users.stream().filter(
                user -> user.getLogin().equals(userlogin)
        )
                .findFirst().get();
    }

    @DeleteMapping("/user/{userlogin}")
    public String delete_user(@PathVariable("userlogin") String userlogin) {
        /*
            Удаляем пользователя по логину
        */

        users.stream().filter(
                user -> user.getLogin().equals(userlogin)
        ).findAny()
                .ifPresent(users::remove);
        return "User " + userlogin + " was deleted";
    }
}
