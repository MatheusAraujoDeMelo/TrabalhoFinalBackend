package com.example.backend.services;

import com.example.backend.dto.GetFilmeByIdDto;
import com.example.backend.dto.GetFilmeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import com.example.backend.repositories.FilmeRepository;
import com.example.backend.entidades.FilmeEntidade;

@Service
public class FilmesService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<GetFilmeResponseDto> getAllFilmes() {
        List<FilmeEntidade> filmes = filmeRepository.findAll();
        if (filmes.isEmpty()) {
            throw new RuntimeException("Nenhum filme encontrado.");
        }
        return filmes.stream()
            .map(f -> new GetFilmeResponseDto(
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

    public GetFilmeResponseDto getFilmeById(GetFilmeByIdDto idDto) {
        FilmeEntidade filme = filmeRepository.findById(idDto.id())
                .orElseThrow(() -> new RuntimeException("Filme não encontrado."));

        return new GetFilmeResponseDto(
                filme.getId(),
                filme.getTitulo(),
                filme.getDiretor(),
                filme.getGenero(),
                filme.getAno(),
                filme.getSinopse(),
                filme.getNotaMedia()
        );
    }


    public GetFilmeResponseDto createFilme(FilmeEntidade filme) {
        if (filme.getTitulo() == null || filme.getTitulo().isBlank()) {
            throw new RuntimeException("Título inválido.");
        }
        FilmeEntidade salvo = filmeRepository.save(filme);

        return new GetFilmeResponseDto(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getDiretor(),
                salvo.getGenero(),
                salvo.getAno(),
                salvo.getSinopse(),
                salvo.getNotaMedia()
        );
    }

    public GetFilmeResponseDto updateFilme(FilmeEntidade filme) {
        FilmeEntidade existente = getFilmeById(filme.getId());
        existente.setTitulo(filme.getTitulo());
        existente.setGenero(filme.getGenero());
        existente.setAno(filme.getAno());
        existente.setDiretor(filme.getDiretor());
        existente.setSinopse(filme.getSinopse());

        FilmeEntidade salvo = filmeRepository.save(filme);
        return new GetFilmeResponseDto(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getDiretor(),
                salvo.getGenero(),
                salvo.getAno(),
                salvo.getSinopse(),
                salvo.getNotaMedia()
        );
    }

    public void deleteFilme(GetFilmeByIdDto id) {
        FilmeEntidade filme = getFilmeById(id);
        filmeRepository.delete(filme);
    }
}