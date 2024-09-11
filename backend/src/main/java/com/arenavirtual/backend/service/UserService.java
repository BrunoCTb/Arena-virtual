package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.repository.PlayerRepository;
import com.arenavirtual.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PlayerRepository playerRepository;

    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    @Transactional
    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Exception -> " + e);
        }
    }

    @Transactional
    public void createPlayer(Player player) {
        try {
            playerRepository.save(player);
        } catch (Exception e) {
            System.out.println("--> " + e);
        }
    }

    public Optional<Player> findPlayerByPublicId(Long id) {
        return playerRepository.findByPublicId(id);
    }

}
