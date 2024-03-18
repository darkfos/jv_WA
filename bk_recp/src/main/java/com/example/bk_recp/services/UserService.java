package com.example.bk_recp.services;

import com.example.bk_recp.entity.User;
import com.example.bk_recp.entity.UserType;
import com.example.bk_recp.repositories.UserRepository;
import com.example.bk_recp.repositories.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    public boolean create_user(User new_user) {

        if (userRepository.findByLogin(new_user.getLogin()).isEnabled() || !new_user.getEmail().isEmpty()) {
            return false;
        } else {
            userRepository.save(new_user);
            UserType usr_tp = userTypeRepository.findById(2L).get();
            new_user.setUserType(usr_tp);
        }
        return true;
    }
}
