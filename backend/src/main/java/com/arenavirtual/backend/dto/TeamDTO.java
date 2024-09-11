package com.arenavirtual.backend.dto;

public record TeamDTO(
        String name,
        Boolean openToInvite,
        String userEmail,
        String logoUrl
)
{
}
