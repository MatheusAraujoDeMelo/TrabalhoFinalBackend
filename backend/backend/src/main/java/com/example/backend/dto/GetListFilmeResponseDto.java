package com.example.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GetListFilmeResponseDto(
        UUID idFilme,
        String tituloFilme,
        String diretorFilme,
        String generoFilme,
        Integer anoFilme,
        String sinopseFilme,
        BigDecimal notaMediaFilme
) {
}
