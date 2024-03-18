package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.User;
import com.example.bk_recp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/registration")
    public String reg_user() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User new_user, Model model) {
        if (userService.create_user(new_user) == false) {
            model.addAttribute("error_text", "Данный пользователь уже существует");
            return "registration";
        }

        return "log";
    }

    @GetMapping("/login")
    public String log_user() {
        return "log";
    }
}
