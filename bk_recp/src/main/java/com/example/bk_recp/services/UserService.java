package com.example.bk_recp.services;

import com.example.bk_recp.entity.User;
import com.example.bk_recp.entity.UserType;
import com.example.bk_recp.repositories.UserRepository;
import com.example.bk_recp.repositories.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;

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
        UserType us_type = userTypeRepository.getById(0L);
        new_user.setUserType(us_type);
        new_user.setPassword(
                passwordEncoder.encode(new_user.getPassword())
        );
        userRepository.save(new_user);
        return true;
    }
}
