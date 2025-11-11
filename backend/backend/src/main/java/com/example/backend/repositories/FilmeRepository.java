package com.example.backend.repositories;

import com.example.backend.entidades.FilmeEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntidade, UUID> {
}
