package com.arenavirtual.backend.controller;

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
    InviteService inviteService;

    @PostMapping("/create")
    public ResponseEntity<String> createTeam(@RequestBody TeamDTO dto) {
        if (teamService.existsByName(dto.name())) {
            return ResponseEntity.badRequest().body("Nome do time já cadastrado!");
        }

        Optional<User> userFound = userService.findByUsernameOrEmail(dto.userEmail(), dto.userEmail());
        if (userFound.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encotrado por email!");
        }

        // add
        Team team = new Team();
        BeanUtils.copyProperties(dto, team);

        team.setCreatedBy(userFound.get());

        teamService.save(team);

        return ResponseEntity.ok("Time criado com sucesso!");
    }


    // TIME ENVIAR CONVITE PARA PLAYER (USER)
    @PostMapping("{id}/invite/send")
    public ResponseEntity<String> teamSendInvite(@PathVariable(name = "id") UUID id, @RequestBody InviteTeamDTO dto) {
        Optional<Player> playerFound = userService.findPlayerByPublicId(dto.playerToSendId());
        if (playerFound.isEmpty()) {
            return ResponseEntity.badRequest().body("Player não encotrado por seu id público!");
        }

        Optional<Team> teamFound = teamService.findById(id);
        if (teamFound.isEmpty()) {
            return ResponseEntity.badRequest().body("Time não encontrado por id");
        }

        // checar se o 'user' que faz referencia ao 'playerTarget' é o mesmo que o atributo created_by do time que esta convidando
        // ou seja: ver se o usuario esta enviando o convite para si mesmo
        if (playerFound.get().getUser().equals(teamFound.get().getCreatedBy())) {
            return ResponseEntity.badRequest().body("Não é possível enviar um convite para si mesmo");
        }

        // caso encontre vai criar um novo convite com os dados do dto (quem envia, para quem, time que é o proprio e status)
        InviteTeam inviteTeam = new InviteTeam(LocalDate.now(), teamFound.get().getCreatedBy(),
                playerFound.get().getUser(), teamFound.get(), InviteStatus.PENDING);

        System.out.println(inviteTeam);
        inviteService.createInviteForTeam(inviteTeam);

        return ResponseEntity.ok("Ok");
    }



}
