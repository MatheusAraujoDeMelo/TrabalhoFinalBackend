package com.example.backend.services;

import com.example.backend.dto.GetListFilmeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.backend.repositories.FilmeRepository;
import com.example.backend.entidades.FilmeEntidade;

@Service
public class FilmesService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<GetListFilmeResponseDto> getAllFilmes() {
        List<FilmeEntidade> filmes = filmeRepository.findAll();
        if (filmes.isEmpty()) {
            throw new RuntimeException("Nenhum filme encontrado.");
        }
        return filmes.stream()
            .map(f -> new GetListFilmeResponseDto(
                    f.getId(),
                    f.getTitulo(),
                    f.getDiretor(),
                    f.getGenero(),
                    f.getAno(),
                    f.getSinopse(),
                    f.getNotaMedia()
            ))
            .toList();
    }

    public FilmeEntidade getFilmeById(UUID id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado."));
    }

    public FilmeEntidade createFilme(FilmeEntidade filme) {
        if (filme.getTitulo() == null || filme.getTitulo().isBlank()) {
            throw new RuntimeException("Título inválido.");
        }
        return filmeRepository.save(filme);
    }

    public FilmeEntidade updateFilme(FilmeEntidade filme) {
        FilmeEntidade existente = getFilmeById(filme.getId());
        existente.setTitulo(filme.getTitulo());
        existente.setGenero(filme.getGenero());
        existente.setAno(filme.getAno());
        existente.setDiretor(filme.getDiretor());
        existente.setSinopse(filme.getSinopse());

        return filmeRepository.save(existente);
    }

    public void deleteFilme(UUID id) {
        FilmeEntidade filme = getFilmeById(id);
        filmeRepository.delete(filme);
    }
}