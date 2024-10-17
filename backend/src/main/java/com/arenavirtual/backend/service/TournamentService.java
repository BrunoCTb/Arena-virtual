package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.team.TeamStats;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.repository.TeamStatsRepository;
import com.arenavirtual.backend.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;
    
    @Autowired
    TeamStatsRepository teamStatsRepository;

    @Transactional
    public void save(Tournament tournament) {
        try {
            tournamentRepository.save(tournament);
        } catch (Exception e) {
            System.out.println("Exception -> " + e);
        }
    }

    public Optional<Tournament> findById(UUID id) {
        return tournamentRepository.findById(id);
    }

    public boolean existsByTitle(String title) {
        return tournamentRepository.existsByTitle(title);
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

	public List<Team> findAllTeams(UUID tournamentId) {
		List<TeamStats> allTeamStats = teamStatsRepository.findAll();

        // filtrar os 'teamStats' pelo tournamentId que foi passado e pegar os times correspondentes
        return allTeamStats.stream()
                .filter(teamStats -> teamStats.getTournament().getId().equals(tournamentId))
                .map(ts -> ts.getTeam())
                .toList();
	}
}
