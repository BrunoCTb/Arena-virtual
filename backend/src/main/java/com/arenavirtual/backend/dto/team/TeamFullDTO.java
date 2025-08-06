package com.arenavirtual.backend.dto.team;

public record TeamFullDTO (
    String name,
    Boolean openToInvite,
    String logoUrl,
    TeamStatsDTO teamStats
)
{
}
