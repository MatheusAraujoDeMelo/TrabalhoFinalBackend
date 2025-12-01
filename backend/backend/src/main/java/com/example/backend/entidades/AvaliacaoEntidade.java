package com.example.backend.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "avaliacao",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"usuario_id", "filme_id"})
        })
public class AvaliacaoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "A nota não pode ser nula")
    @Min(value = 0, message = "A nota mínima permitida é 0")
    @Max(value = 10, message = "A nota máxima permitida é 10")
    private Integer nota;

    @Lob
    private String comentario;

    private LocalDateTime dataExpiracao;

    @PrePersist
    public void prePersist() {
        dataExpiracao = LocalDateTime.now();
    }

    @JsonBackReference("filme-avaliacoes")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filme_id", nullable = false)
    private FilmeEntidade filme;

    @JsonBackReference("usuario-avaliacoes")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntidade usuario;


}
