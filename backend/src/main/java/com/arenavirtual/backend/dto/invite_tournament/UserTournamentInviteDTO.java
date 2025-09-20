package com.arenavirtual.backend.dto.invite_tournament;

import com.arenavirtual.backend.model.entity.invite.InviteTournament;

public record UserTournamentInviteDTO (
        boolean isSent, // Recebido ou enviado
        InviteTournament tournamentInvite
)
{
}
