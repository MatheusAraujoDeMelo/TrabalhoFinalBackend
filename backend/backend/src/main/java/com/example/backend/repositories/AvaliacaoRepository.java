package com.example.backend.repositories;

import com.example.backend.entidades.AvaliacaoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoEntidade, UUID> {
}
