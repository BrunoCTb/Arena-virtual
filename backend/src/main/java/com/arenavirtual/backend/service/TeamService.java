package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Transactional
    public void save(Team team) {
        try {
            // criar teamStats vazio ao criar um time novo
            teamRepository.save(team);
        } catch (Exception e) {
            System.out.println("Exception ---> " + e);
        }
    }

    public boolean existsByName(String name) {
        return teamRepository.existsByName(name);
    }

    public Optional<Team> findById(UUID id) {
        return teamRepository.findById(id);
    }

}
