package com.arenavirtual.backend.controller.invite;

import com.arenavirtual.backend.dto.invite_tournament.TournamentInviteRequestDTO;
import com.arenavirtual.backend.dto.invite_tournament.UserTournamentInviteDTO;
import com.arenavirtual.backend.model.entity.invite.InviteTournament;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.model.inviteType.TournamentInviteType;
import com.arenavirtual.backend.service.TeamService;
import com.arenavirtual.backend.service.TournamentInviteService;
import com.arenavirtual.backend.service.TournamentService;
import com.arenavirtual.backend.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@CrossOrigin("*")
public class TournamentInviteController {

    @Autowired
    TournamentInviteService tournamentInviteService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    // CRIAR DE TOURNAMENT -> PLAYER E VICE-VERSA
    @PostMapping("/tournament/invite/create")
    public ResponseEntity<String> createInvite(@RequestBody TournamentInviteRequestDTO dto) {
        Tournament tournament = tournamentService.findById(dto.tournamentId())
                .orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado!"));
        Team team = teamService.findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado!"));


        boolean inviteRegistered = tournamentInviteService.existsByTournamentIdAndTeamIdAndSenderType(
                dto.tournamentId(), dto.teamId(), dto.senderType()
        );

        if (inviteRegistered) {
            throw new IllegalArgumentException("Esse convite já foi enviado!");
        }

        tournamentInviteService.createTournamentTeamInvite(tournament, team, dto);

        return ResponseEntity.ok("Convite enviado com sucesso!");
    }

    @GetMapping("/user/tournament/invites")
    public ResponseEntity<List<UserTournamentInviteDTO>> getUserTournamentInvites() {
        User user = userService.getLoggedUser()
                .orElseThrow(() -> new EntityNotFoundException("user nao logado!"));

        List<UserTournamentInviteDTO> invites = tournamentInviteService.findAllByUserId(user.getId());

        return ResponseEntity.ok(invites);
    }

}
