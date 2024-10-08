package com.arenavirtual.backend.controller;

import com.arenavirtual.backend.dto.InviteResponseDTO;
import com.arenavirtual.backend.dto.InviteTeamDTO;
import com.arenavirtual.backend.dto.TeamDTO;
import com.arenavirtual.backend.model.entity.player.Player;
import com.arenavirtual.backend.model.entity.team.InviteTeam;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.model.inviteStatus.InviteStatus;
import com.arenavirtual.backend.service.InviteService;
import com.arenavirtual.backend.service.TeamService;
import com.arenavirtual.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @Autowired
    InviteService inviteTeamService;

    @PostMapping("/create")
    public ResponseEntity<String> createTeam(@RequestBody TeamDTO dto) {
        if (teamService.existsByName(dto.name())) {
            return ResponseEntity.badRequest().body("Nome do time já cadastrado!");
        }

        User user = userService.findByUsernameOrEmail(dto.userEmail(), dto.userEmail()).
        	orElseThrow(() -> new IllegalArgumentException("Usuário não encotrado por email!"));

        // add
        Team team = new Team();
        BeanUtils.copyProperties(dto, team);

        team.setCreatedBy(user);

        teamService.save(team);

        return ResponseEntity.ok("Time criado com sucesso!");
    }

    @GetMapping("/all")
    public List<Team> listAllTeams() {
        return teamService.findAll();
    }

    // TIME ENVIAR CONVITE PARA PLAYER (USER)
    @PostMapping("/{id}/invite/send")
    public ResponseEntity<String> teamSendInvite(@PathVariable(name = "id") UUID id, @RequestBody InviteTeamDTO dto) {
        Player player = userService.findPlayerByPublicId(dto.playerToJoin())
                .orElseThrow(() -> new IllegalArgumentException("Player não encotrado por seu id público!"));

        Team team = teamService.findById(id).orElseThrow(() -> new IllegalArgumentException("ime não encontrado por id"));

        // checar se o 'user' que faz referencia ao 'playerTarget' é o mesmo que o atributo created_by do time que esta convidando
        // ou seja: ver se o usuario esta enviando o convite para si mesmo
        if (player.getUser().equals(team.getCreatedBy())) {
            return ResponseEntity.badRequest().body("Não é possível enviar um convite para si mesmo");
        }

        // caso encontre vai criar um novo convite com os dados do dto (quem envia, para quem, time que é o proprio e status)
        InviteTeam inviteTeam = new InviteTeam(LocalDate.now(), team.getCreatedBy(),
                player.getUser(), team, InviteStatus.PENDING);

        inviteTeamService.createInviteForTeam(inviteTeam);

        return ResponseEntity.ok("Ok");
    }

    // PLAYER SOLICITA PARA ENTRAR EM UM TIME
    @PostMapping("/{id}/joinrequest")
    public ResponseEntity<String> playerJoin(@PathVariable(name = "id") UUID id, @RequestBody InviteTeamDTO dto) {
        Team team = teamService.findById(id).orElseThrow(() -> new IllegalArgumentException("Time não encontrado por id!"));

        Player player = userService.findPlayerByPublicId(dto.playerToJoin()).
                orElseThrow(() -> new IllegalArgumentException("Player não encotrado por seu id público!"));

        if (!team.getOpenToInvite()) {
            return ResponseEntity.badRequest().body("O time não permite solicitações para entrar");
        }

        if (player.getUser().equals(team.getCreatedBy())) {
            return ResponseEntity.badRequest().body("Não é possível enviar um convite para si mesmo");
        }

        InviteTeam inviteTeam = new InviteTeam(LocalDate.now(), player.getUser(), team.getCreatedBy(),
                team, InviteStatus.PENDING);

        inviteTeamService.createInviteForTeam(inviteTeam);

        return ResponseEntity.ok("Solicitação para entrar no time " + team.getName() + " criada com sucesso!");
    }

    // Ver todos os convites que foram enviados pelo time
    @GetMapping("/{teamId}/invite/invites/sent")
    public List<InviteTeam> getSentInvitations(@PathVariable(name = "teamId") UUID teamId) {
        // 1: buscar todos os convites que referenciam o time (ou seja, targetTeam é o time)
        List<InviteTeam> invitations = inviteTeamService.findByTeamId(teamId);

        // 2: filtrar os convites por apenas os que o time enviou, então:
        // se o user que enviou for o mesmo do createdBy do time
        return invitations.stream()
                .filter(invite -> invite.getInvitedBy().equals(invite.getTeamTarget().getCreatedBy()))
                .toList();
    }

    // Ver todos os convites que foram enviados pelo time
    @GetMapping("/{teamId}/invite/invites/received")
    public List<InviteTeam> getReceivedInvitations(@PathVariable(name = "teamId") UUID teamId) {
        List<InviteTeam> invitations = inviteTeamService.findByTeamId(teamId);

        // mesmo que o metodo acima, mas trocando quem recebe por quem envia,
        // assim quem enviou é o player para o time, entao, os convites que o time recebeu
        return invitations.stream()
                .filter(invite -> invite.getInvitedTarget().equals(invite.getTeamTarget().getCreatedBy()))
                .toList();
    }

    // Resposta de convite do time -> para o player
    @PostMapping("/{teamId}/invite/{inviteId}")
    public ResponseEntity<String> teamInviteResponse(@PathVariable(name = "teamId") UUID teamId,
                                                     @PathVariable(name = "inviteId") UUID inviteId,
                                                     @RequestBody InviteResponseDTO response) {

        teamService.findById(teamId).orElseThrow(() -> new IllegalArgumentException("time não encontrado!"));

        InviteTeam invite = inviteTeamService.findById(inviteId).
        		orElseThrow(() -> new IllegalArgumentException("convite não encontrado!"));

        // convite, resposta (aceita ou nao)
        inviteTeamService.inviteResponse(invite, response.acceptInvite());

        return ResponseEntity.ok("Player ");
    }


}
