package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;

    @Transactional
    public void save(Tournament tournament) {
        try {
            tournamentRepository.save(tournament);
        } catch (Exception e) {
            System.out.println("Exception -> " + e);
        }
    }

    public Optional<Tournament> findById(UUID id) {
        for (Tournament t : tournamentRepository.findAll()) {
            System.out.println(t.toString());
        }

        return tournamentRepository.findById(id);
    }

    public boolean existsByTitle(String title) {
        return tournamentRepository.existsByTitle(title);
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }
}
