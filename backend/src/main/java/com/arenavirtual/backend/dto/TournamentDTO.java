package com.arenavirtual.backend.dto;

public record TournamentDTO(
        String title,
        String modality,
        Boolean onlineMode,
        Integer teamsQuantity,
        String imageRepresentationUrl,
        String format
) {
}
