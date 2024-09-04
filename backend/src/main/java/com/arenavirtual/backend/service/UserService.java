package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Transactional
    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Exception -> " + e);
        }
    }
}
