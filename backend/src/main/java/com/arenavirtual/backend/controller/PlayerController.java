package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.service.InviteService;
import com.arenavirtual.backend.service.PlayerService;
import com.arenavirtual.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class PlayerController {

    @Autowired
    UserService userService;

    @Autowired
    InviteService inviteService;

    @Autowired
    PlayerService playerService;


    @GetMapping("/{username}/invites/received")
    public List<InviteTeam> getUserReceivedInvitations(@PathVariable(name="username") String username) {
        Optional<User> u = userService.findByUsernameOrEmail(username, username);
        if (!u.isEmpty()) {
            List<InviteTeam> invites = inviteService.findReceivedInvitations(u.get());
            return invites;
        }

        return List.of();
    }

    @GetMapping("/{username}/invites/sent")
    public List<InviteTeam> getUserSentInvitations(@PathVariable(name="username") String username) {
        List<InviteTeam> invites;

        Optional<User> u = userService.findByUsernameOrEmail(username, username);
        if (!u.isEmpty()) {
            invites = inviteService.findSentInvitations(u.get());
            return invites;
        }

        return List.of();
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.findAll();
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable(name = "playerId") Long playerId) {
        Optional<Player> player = userService.findPlayerByPublicId(playerId);

        if (player.isEmpty()) {
            throw new EntityNotFoundException("player nao encontrado");
        }

        return ResponseEntity.ok(player.get());

    }

}
