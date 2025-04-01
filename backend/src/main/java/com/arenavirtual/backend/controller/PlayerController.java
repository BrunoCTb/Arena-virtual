package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.service.InviteService;
import com.arenavirtual.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
