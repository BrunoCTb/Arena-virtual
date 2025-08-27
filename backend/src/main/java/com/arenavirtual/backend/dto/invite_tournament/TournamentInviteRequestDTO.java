package com.arenavirtual.backend.dto.invite_tournament;

import com.arenavirtual.backend.model.inviteType.TournamentInviteType;

import java.util.UUID;

public record TournamentInviteRequestDTO(
    UUID tournamentId,
    UUID teamId,
    String message,
    TournamentInviteType senderType,
    TournamentInviteType receiverType
)
{
}
