package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.repository.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InviteService {

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    TeamService teamService;

    public void createInviteForTeam(InviteTeam invite) {
        inviteRepository.save(invite);
    }

    public List<InviteTeam> findByTeamId(UUID teamId) {

        Optional<Team> team = teamService.findById(teamId);
        if (team.isEmpty()) {
            return List.of();
        }

        return inviteRepository.findByTeamTarget(team.get());
    }



}
