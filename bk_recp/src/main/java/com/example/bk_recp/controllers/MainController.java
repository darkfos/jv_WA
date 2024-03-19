package com.example.bk_recp.controllers;

import com.example.bk_recp.entity.User;
import com.example.bk_recp.entity.UserType;
import com.example.bk_recp.repositories.UserTypeRepository;
import com.example.bk_recp.services.NoteService;
import com.example.bk_recp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    //Приём HTTP request

    private final NoteService noteService;
    private final UserService userService;
    private final UserTypeRepository userTypeRepository;

    /**
     * Главная страница
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/")
    public String main_page(Model model, Principal principal) {
        model.addAttribute("user", noteService.getUserByPrincipal(principal));

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


        return "main_page";
    }

    /**
     * Страница мой профиль
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/my-profile")
    public String my_profile(Model model, Principal principal) {

        User user = noteService.getUserByPrincipal(principal);
        model.addAttribute("user", user);

        //Getting size attributes for profile
        int size_recipes = userService.get_all_recipes_user(user.getId_user());
        int size_notes = userService.get_all_notes_user(user.getId_user());
        model.addAttribute("size_notes", size_notes);
        model.addAttribute("size_recipes", size_recipes);
        return "my_profile";
    }

    /**
     * Обновление имени пользователя
     * @param new_name
     * @param id_user
     * @param model
     * @param principal
     * @return
     */
    @PostMapping("/update_profile")
    public String upd_profile(@RequestParam("new_name") String new_name, @RequestParam("id_user") Long id_user, Model model, Principal principal) {
        User user = userService.update_user(id_user, new_name);
        model.addAttribute("user_data", user);
        model.addAttribute("user", user);
        return "redirect:/my-profile";
    }

    /**
     * Удаление пользователя
     * @param id_user
     * @param model
     * @return
     */
    @PostMapping("/delete_profile")
    public String del_profile(@RequestParam("id_user") Long id_user, Model model, Principal principal) {
        userService.del_user(id_user);
        model.addAttribute("user", noteService.getUserByPrincipal(principal));
        return "redirect:/login?logout";
    }
}
