package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmeExternoDTO {

    private String titulo;
    private Integer ano;
    private String diretor;
    private String genero;
    private String poster;
}