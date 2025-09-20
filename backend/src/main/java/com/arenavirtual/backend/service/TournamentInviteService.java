package com.arenavirtual.backend.service;

import com.arenavirtual.backend.dto.invite_tournament.TournamentInviteRequestDTO;
import com.arenavirtual.backend.dto.invite_tournament.UserTournamentInviteDTO;
import com.arenavirtual.backend.model.entity.invite.InviteTournament;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.model.entity.user.User;
import com.arenavirtual.backend.model.inviteStatus.InviteStatus;
import com.arenavirtual.backend.model.inviteType.TournamentInviteType;
import com.arenavirtual.backend.repository.TournamentInviteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TournamentInviteService {

    @Autowired
    TournamentInviteRepository tournamentInviteRepository;

    @Autowired
    UserService userService;


    public void createTournamentTeamInvite(Tournament tournament, Team team, TournamentInviteRequestDTO inviteRequest) {
        InviteTournament inviteTournament = new InviteTournament();

        inviteTournament.setTournament(tournament);
        inviteTournament.setTeam(team);
        inviteTournament.setInviteStatus(InviteStatus.PENDING);
        inviteTournament.setSenderType(inviteRequest.senderType());

        tournamentInviteRepository.save(inviteTournament);
    }

    public boolean existsByTournamentIdAndTeamIdAndSenderType(UUID tournamentId, UUID teamId, TournamentInviteType type) {
        return tournamentInviteRepository.existsByTournamentIdAndTeamIdAndSenderType(tournamentId, teamId, type);
    }

    public List<InviteTournament> findAll() {
        return tournamentInviteRepository.findAll();
    }

    public List<UserTournamentInviteDTO> findAllByUserId(UUID userId) {
        List<UserTournamentInviteDTO> invites = new ArrayList<>();
        User user = userService.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user nao encontrado!"));

        List<InviteTournament> allInvites = tournamentInviteRepository.findAllByUser(userId);

        System.out.println(user);
        System.out.println("---->> ");


        for (InviteTournament it : allInvites) {
            // se o sender for time -> torn. e o dono do time for o mesmo do user
            if ((it.getSenderType() == TournamentInviteType.TEAM && it.getTeam().getCreatedBy().getId().equals(user.getId()))
                    || (it.getSenderType() == TournamentInviteType.TOURNAMENT && it.getTournament().getCreatedBy().getId().equals(user.getId()))) {
                invites.add(new UserTournamentInviteDTO(true, it));
            } else {
                invites.add(new UserTournamentInviteDTO(false, it));
            }
        }

        return invites;
    }

}
