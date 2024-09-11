package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.repository.InviteRepository;
import com.arenavirtual.backend.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteService {

    @Autowired
    InviteRepository inviteRepository;

    public void createInviteForTeam(InviteTeam invite) {
        inviteRepository.save(invite);
    }
}
