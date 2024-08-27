package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.service.TournamentService;
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

}
