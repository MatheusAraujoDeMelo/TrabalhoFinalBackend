package com.example.backend.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "filme")
public class FilmeEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "O titulo não pode ser nulo")
    private String titulo;

    private String diretor;

    private String genero;

    @NotNull(message = "O ano não pode ser nulo")
    @Min(value = 1960, message = "O ano não pode ser inferior a 1960")
    @Max(value = 2026, message = "O ano não pode ser superior a 2026")
    private Integer ano;

    @Lob
    private String sinopse;

    // LINHA DUPLICADA REMOVIDA DAQUI

    @Column(name = "nota_media", precision = 3, scale = 2) // scale = 2 para o (3,2) do BD
    private BigDecimal notaMedia; // <-- DEIXE SÓ ESTE

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL)
    private List<AvaliacaoEntidade> avaliacoes;

}