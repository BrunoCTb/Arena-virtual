package com.arenavirtual.backend.service;

import com.arenavirtual.backend.dto.invite_tournament.TournamentInviteRequestDTO;
import com.arenavirtual.backend.model.entity.invite.InviteTournament;
import com.arenavirtual.backend.model.entity.team.Team;
import com.arenavirtual.backend.model.entity.tournament.Tournament;
import com.arenavirtual.backend.model.inviteStatus.InviteStatus;
import com.arenavirtual.backend.model.inviteType.TournamentInviteType;
import com.arenavirtual.backend.repository.TournamentInviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TournamentInviteService {

    @Autowired
    TournamentInviteRepository tournamentInviteRepository;


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

}
