package com.arenavirtual.backend.dto;

public record TournamentDTO(
        String title,
        String modality,
        Boolean onlineMode,
        Integer minTeams,
        Integer maxTeams,
        String imageRepresentationUrl,
        String format
) {
}
