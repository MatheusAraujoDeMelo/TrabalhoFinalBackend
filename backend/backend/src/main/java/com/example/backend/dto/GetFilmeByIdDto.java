package com.example.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record GetFilmeByIdDto(
        @NotNull(message = "ID não pode ser nulo")
        @Positive(message = "ID deve ser positivo")
        UUID id
) {
}
