package com.arenavirtual.backend.dto;

import java.time.LocalDate;

public record UserDTO
    (
            String username,
            String firstName,
            String lastName,
            String email,
            String password,
            String confirmPassword,
            LocalDate birth,
            String imageUrl

    )
{}
