package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.TournamentDTO;
import com.arenavirtual.backend.dto.tournament.StartTournamentDTO;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.team.TeamStats;
import com.arenavirtual.backend.model.entity.tournament.Format;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.service.TeamService;
import com.arenavirtual.backend.service.TournamentService;
import com.arenavirtual.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tournament")
@CrossOrigin("*")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @GetMapping("/{tournamentId}")
    public ResponseEntity<Tournament> getTournament(@PathVariable("tournamentId") UUID tournamentId) {
        return ResponseEntity.ok(tournamentService.findById(tournamentId).
                orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado!")));
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

        User user = userService.getLoggedUser()
                .orElseThrow(() -> new EntityNotFoundException("usuário não logado"));

        // adicionar o torneio
        Tournament newTournament = new Tournament();
        BeanUtils.copyProperties(dto, newTournament);

        newTournament.setCreatedBy(user);

        tournamentService.save(newTournament);

        return ResponseEntity.ok("Campeonato criado com sucesso");
    }
    
    @GetMapping("/{tournamentId}/findteams")
	public List<Team> findTournamentTeams(@PathVariable("tournamentId") UUID tournamentId) {
        tournamentService.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("O campeonato não foi encontrado!"));

        if (tournamentService.findAllTeams(tournamentId).isEmpty()) {
            return List.of();
        }

    	return tournamentService.findAllTeams(tournamentId);
    }


    // cria o team_stats dele e consequentemente adiciona o time no torneio
    @PostMapping("/{tournamentId}/addteam/{teamId}")
    public ResponseEntity<String> addTeamToTournament(@PathVariable("tournamentId") UUID tournamentId, @PathVariable("teamId") UUID teamId) {
        Tournament tournament = tournamentService.findById(tournamentId)
                .orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado!"));

        Team team = teamService.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado!"));

        TeamStats teamStats = new TeamStats(team, tournament);

        if (teamService.isRegistered(teamStats)) {
            String errorMsg = "Time já foi adicionado no torneio '" +
                    teamStats.getTournament().getTitle() + "'!";

            throw new IllegalArgumentException(errorMsg);
        }

        teamService.createTeamStats(teamStats);

        return ResponseEntity.ok("Time adicionado com sucesso!");
    }
    
    // metodo que ira servir apenas para realizar as primeiras configurações do campeonato,
    // ou seja, neste ponto o campeonato nao foi 'startado', com isso,
    // serao realizadas coisas como realizar as divisoes dos times, criar as partidas iniciais, salvar os times no db
    @PostMapping("/{tournamentId}/start")
    public ResponseEntity<String> startTournament(@PathVariable("tournamentId") UUID tournamentId, StartTournamentDTO dto) {
    	
    	Format formatMataMata = new Format("mata-mata", 2, 1, "apenas um time ira passar, nesse caso campeao");
    	
    	Tournament tournament = tournamentService.findById(tournamentId)
    			.orElseThrow(() -> new IllegalArgumentException("Torneio não encontrado"));
    	
    	if (dto.start() != true) {
    		return ResponseEntity.badRequest().body("solicitação inváilida");
    	}    	
    	
    	// topicos importantes para validar um inicio:
    	// - numero de times deve estar entre a correspondencia
    	if (!tournamentService.isValidToStart(tournament)) {
    		return ResponseEntity.badRequest().body("Condições do campeonato inválidas para poder iniciar!");
    	}
    	
    	// * se estiver com todas as condicoes validas:
    	
    	// pegar os times do campeonato e gerar a separacao e armazenar em memoria
    	List<Team> tournamentTeams = tournamentService.findAllTeams(tournamentId);
    	

    	// salvar os times em uma matriz no db
    	
    	// gerar as partidas a partir da separacao realizada
    	
    	// salvar cada partida no db em 'Match'
    	
    	// modificar o status para iniciado
    	
    	
    	
    	return ResponseEntity.ok("campeonato iniciado [BÁSICO]");
    }

}
