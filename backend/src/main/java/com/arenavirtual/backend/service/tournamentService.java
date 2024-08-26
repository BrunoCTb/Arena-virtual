package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class tournamentService {

    @Autowired
    TournamentRepository tournamentRepository;

    @Transactional
    public void save(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    @Transactional
    public void remove(UUID tournamentId) {
        tournamentRepository.deleteById(tournamentId);
    }


}
