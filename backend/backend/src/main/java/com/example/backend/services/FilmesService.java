package com.example.backend.services;

import com.example.backend.dto.FilmeExternoDTO;
import com.example.backend.dto.GetListFilmeResponseDto;
import com.example.backend.dto.OmdbSearchResponseDTO;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;
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

    private static final String API_URL = "http://www.omdbapi.com/?apikey=dc3b72ec&s=";

    public List<FilmeExternoDTO> buscarFilmesAPIExterna(String query) {

        RestTemplate restTemplate = new RestTemplate();

        String url = API_URL + query.replace(" ", "+");

        OmdbSearchResponseDTO resposta = restTemplate.getForObject(url, OmdbSearchResponseDTO.class);

        if (resposta == null ||
                !"True".equalsIgnoreCase(resposta.getResponse()) ||
                resposta.getSearch() == null) {

            return List.of();
        }

        return resposta.getSearch().stream()
            .map(omdb -> new FilmeExternoDTO(
                    omdb.getTitle(),
                    parseAno(omdb.getYear()),
                    null, 
                    null, 
                    omdb.getPoster()
            ))
            .collect(Collectors.toList());
    }

    private Integer parseAno(String year) {
        try {
            return Integer.parseInt(year);
        } catch (Exception e) {
            return null;
        }
    }
}