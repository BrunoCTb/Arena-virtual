package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.TournamentDTO;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.team.TeamStats;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.service.TeamService;
import com.arenavirtual.backend.service.TournamentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tournament")
@CrossOrigin("*")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getTournament(@PathVariable("id") UUID id) {
        Optional<Tournament> tournament = tournamentService.findById(id);

        if (tournament.isEmpty()) {
            return ResponseEntity.badRequest().body("Torneio não encontrado");
        }

        System.out.println("-> " + tournament);

        return ResponseEntity.ok().body(tournament.toString() + "\n");
    }

    @GetMapping("/all")
    public List<Tournament> listAllTournaments() {
        return tournamentService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTournament(@RequestBody TournamentDTO dto) {
        // verificar se ja existe pelo nome (independente de captalize -> AaAa)
        if (tournamentService.existsByTitle(dto.title())) {
            ResponseEntity.badRequest().body("Já há um campeonato criado com esse nome");
        }

        System.out.println(dto);

        // adicionar o torneio
        Tournament newTournament = new Tournament();
        BeanUtils.copyProperties(dto, newTournament);

        tournamentService.save(newTournament);

        return ResponseEntity.ok("Campeonato criado com sucesso");
    }
    
    @GetMapping("/{tournamentId}/findteams")
	public List<Team> findTournamentTeams(@PathVariable("tournamentId") UUID tournamentId) {
    	return tournamentService.findAllTeams(tournamentId);
    }


    // cria o team_stats dele e consequentemente adiciona o time no torneio
    @PostMapping("/{id}/addteam/{teamId}")
    public ResponseEntity<String> addTeamToTournament(@PathVariable("id") UUID id, @PathVariable("teamId") UUID teamId) {
        Optional<Team> team = teamService.findById(teamId);
        Optional<Tournament> tournament = tournamentService.findById(id);

        TeamStats teamStats = new TeamStats(team.get(), tournament.get());

        teamService.createTeamStats(teamStats);

        return ResponseEntity.ok("team stats criado!");
    }

}
