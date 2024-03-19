package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.User;
import com.example.bk_recp.entity.UserType;
import com.example.bk_recp.repositories.UserTypeRepository;
import com.example.bk_recp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;
    public final UserTypeRepository userTypeRepository;

    @GetMapping("/registration")
    public String reg_user() {
        UserType usr_user = new UserType();
        usr_user.setId_user_type(0L);
        usr_user.setName_type("user");
        usr_user.setCode_type(1);

        UserType usr_admin = new UserType();
        usr_admin.setId_user_type(1L);
        usr_admin.setName_type("admin");
        usr_admin.setCode_type(2);

        List<UserType> all_usr_types= userTypeRepository.findAll();

        if (all_usr_types.size() < 2) {
            userTypeRepository.save(usr_user);
            userTypeRepository.save(usr_admin);
        }
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
