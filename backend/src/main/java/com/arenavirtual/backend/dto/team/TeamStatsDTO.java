package com.arenavirtual.backend.dto.team;

public record TeamStatsDTO (
    Integer wins,
    Integer losses,
    Integer draws,
    Integer matchesPlayed,
    Integer currentPosition
)
{

}
