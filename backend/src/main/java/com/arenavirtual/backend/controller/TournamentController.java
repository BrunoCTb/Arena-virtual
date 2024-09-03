package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.TournamentDTO;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.service.TournamentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getTournament(@PathVariable("id") UUID id) {
        Optional<Tournament> tournament = tournamentService.findById(id);

        if (tournament.isEmpty()) {
            return ResponseEntity.badRequest().body("Torneio não encontrado");
        }

        System.out.println("-> " + tournament);

        return ResponseEntity.ok().body(tournament.toString() + "\n");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTournament(@RequestBody TournamentDTO dto) {
        // verificar se ja existe pelo nome (independente de captalize -> AaAa)
        if (tournamentService.existsByTitle(dto.title())) {
            ResponseEntity.badRequest().body("Já há um campeonato criado com esse nome");
        }

        // adicionar o torneio
        Tournament newTournament = new Tournament();
        BeanUtils.copyProperties(dto, newTournament);

        tournamentService.save(newTournament);

        return ResponseEntity.ok("Campeonato criado com sucesso");
    }

}
