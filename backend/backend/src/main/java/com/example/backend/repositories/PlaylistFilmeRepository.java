package com.example.backend.repositores;

import com.example.backend.entidades.PlaylistFilmeEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaylistFilmeRepository extends JpaRepository<PlaylistFilmeEntidade, UUID> {
    List<PlaylistFilmeEntidade> findByPlaylistId(UUID playlistId);
}
