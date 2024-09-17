package com.arenavirtual.backend.service;

import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.repository.InviteRepository;
import com.arenavirtual.backend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InviteService {

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @Autowired
    PlayerService playerService;

    @Transactional
    public void deleteById(UUID inviteId) {
        inviteRepository.deleteById(inviteId);
    }

    public void createInviteForTeam(InviteTeam invite) {
        inviteRepository.save(invite);
    }

    public Optional<InviteTeam> findById(UUID id) {
        return inviteRepository.findById(id);
    }

    public List<InviteTeam> findByTeamId(UUID teamId) {
        Optional<Team> team = teamService.findById(teamId);
        if (team.isEmpty()) {
            return List.of();
        }

        return inviteRepository.findByTeamTarget(team.get());
    }

    // lidar com a resposta do time para o player e vice-versa
    public void inviteResponse(InviteTeam invite, Boolean response) {
        // CASO A RESPOSTA FOR ACEITAR
        if (response) {
            // por padrão pegar o usuario que e enviou
            User playerToAdd = invite.getInvitedBy();

            // checar se o usuario que enviou é o mesmo que criou o time, ou seja,
            // o usuario (player) a ser adicionado no time precisa ser diferente do "dono" do time
            if (invite.getInvitedBy().equals(invite.getTeamTarget().getCreatedBy())) {
                playerToAdd = invite.getInvitedTarget();
            }

            // Pegar o player que é o mesmo usuario do user
            Player player = userService.findPlayerByUser(playerToAdd).get();

            // setar o time para o que ira ser mudado
            player.setTeam(invite.getTeamTarget());

            // atualizar o player setado
            playerService.save(player);
        }

        // CASO RECUSE
        // exclui o invite independente do if
        this.deleteById(invite.getId());
    }



}
