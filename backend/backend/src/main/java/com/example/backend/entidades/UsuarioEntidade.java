package com.example.backend.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class UsuarioEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @JsonManagedReference("usuario-avaliacoes")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<AvaliacaoEntidade> avaliacoes;
}
