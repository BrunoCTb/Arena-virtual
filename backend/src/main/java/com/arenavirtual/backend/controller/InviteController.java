package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.InviteResponseDTO;
import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/invite")
@CrossOrigin("*")
public class InviteController {

    @Autowired
    InviteService inviteService;


    @GetMapping("/{inviteId}")
    public ResponseEntity<InviteTeam> getInvite(@RequestBody @PathVariable("inviteId") UUID inviteId) {
        InviteTeam inviteTeam = inviteService.findById(inviteId)
                .orElseThrow(() -> new IllegalArgumentException("convite não encontrado!"));

        return ResponseEntity.ok(inviteTeam);
    }

    @PostMapping("/{inviteId}")
    public ResponseEntity<String> inviteResponse(@PathVariable(name = "inviteId") UUID inviteId,
                                                     @RequestBody InviteResponseDTO response) {

        InviteTeam invite = inviteService.findById(inviteId).
                orElseThrow(() -> new IllegalArgumentException("convite não encontrado!"));

        // convite, resposta (aceita ou nao)
        inviteService.inviteResponse(invite, response.acceptInvite());

        return ResponseEntity.ok("Convite alterado!");
    }

}
