package com.example.bk_recp.services;

import com.example.bk_recp.entity.Note;
import com.example.bk_recp.entity.Recipes;
import com.example.bk_recp.entity.User;
import com.example.bk_recp.entity.UserType;
import com.example.bk_recp.repositories.NoteRepository;
import com.example.bk_recp.repositories.RecipesRepository;
import com.example.bk_recp.repositories.UserRepository;
import com.example.bk_recp.repositories.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecipesRepository recipesRepository;
    private final NoteRepository noteRepository;

    /**
     * Создание пользователя
     * @param new_user
     * @return
     */
    public boolean create_user(User new_user) {
        System.out.println(new_user.getLogin());

        //Created User?
        if (userRepository.findByLogin(new_user.getLogin()) != null) {
            System.out.println("Данный пользователь уже существует");
            return false;
        }

        //User is valid?
        if (new_user.getLogin().length() < 4 || new_user.getPassword().length() < 5 || new_user.getEmail().isEmpty()) {
            return false;
        }

        System.out.println("Данный пользователь ещё не создан");
        List<UserType> us_type = userTypeRepository.findAll();

        Long id = 0L;
        for (UserType usr : us_type) {
            if (usr.getName_type().equals("user")) {
                id = usr.getId_user_type();
            }
        }
        new_user.setUserType(userTypeRepository.getById(id));
        new_user.setPassword(
                passwordEncoder.encode(new_user.getPassword())
        );

        //Date registration
        LocalDate localdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date lc_date = Date.valueOf(localdate.format(formatter));

        new_user.setDate_reg(lc_date);
        new_user.setName_user(new_user.getLogin());
        userRepository.save(new_user);
        return true;
    }


    /**
     * Обновление имени пользователя
     * @param user
     * @return
     */
    public User update_user(Long id_user, String new_name) {
        User user = userRepository.findById(id_user).orElse(null);
        user.setName_user(new_name);
        userRepository.save(user);
        return user;
    }

    /**
     * Удаление пользователя по id
     * @param user_id
     */
    public void delete_user(Long user_id) {
        userRepository.deleteById(user_id);
    }

    /**
     * Получение количества рецептов пользователя
     * @param user_id
     * @return
     */
    public int get_all_recipes_user(Long user_id) {
        ArrayList<Recipes> all_recp = (ArrayList<Recipes>) recipesRepository.findAll();

        ArrayList<Recipes> result = new ArrayList<>();

        for (Recipes recipes : all_recp) {
            User usr = recipes.getUser();
            if (usr.getId_user().equals(user_id)) {
                result.add(recipes);
            }
        }

        return result.size();
    }

    /**
     * Получение количества заметок пользователя
     * @param user_id
     * @return
     */
    public int get_all_notes_user(Long user_id) {
        ArrayList<Note> all_notes = (ArrayList<Note>) noteRepository.findAll();

        ArrayList<Note> result = new ArrayList<>();

        for (Note note : all_notes) {
            User usr = note.getUser();
            if (usr.getId_user().equals(user_id)) {
                result.add(note);
            }
        }

        return result.size();
    }

    /**
     * Удаление пользователя по id
     * @param user_id
     */
    public void del_user(Long user_id) {
        userRepository.deleteById(user_id);
    }
}
