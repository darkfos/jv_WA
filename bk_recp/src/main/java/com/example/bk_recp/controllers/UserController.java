package com.example.bk_recp.controllers;

import com.example.bk_recp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/registration")
    public String reg_user() {
        return "registration";
    }
}
