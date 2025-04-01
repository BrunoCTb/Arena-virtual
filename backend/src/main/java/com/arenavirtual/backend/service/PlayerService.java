package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public void save(Player player) {
        playerRepository.save(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

}
