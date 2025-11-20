package com.example.backend.controllers;

import com.example.backend.dto.FilmeExternoDTO;
import com.example.backend.dto.GetListFilmeResponseDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend.services.FilmesService;
import com.example.backend.entidades.FilmeEntidade;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/filmes")
public class FilmesController {

    @Autowired
    private FilmesService filmesService;

    @GetMapping
    public List<GetListFilmeResponseDto> getAllFilmes() {
        return filmesService.getAllFilmes();
    }

    @GetMapping("/{id}")
    public FilmeEntidade getFilmeById(@PathVariable UUID id) {
        return filmesService.getFilmeById(id);
    }

    @PostMapping
    public FilmeEntidade createFilme(@RequestBody FilmeEntidade filme) {
        return filmesService.createFilme(filme);
    }

    @PutMapping
    public FilmeEntidade updateFilme(@RequestBody FilmeEntidade filme) {
        return filmesService.updateFilme(filme);
    }

    @DeleteMapping("/{id}")
    public String deleteFilme(@PathVariable UUID id) {
        filmesService.deleteFilme(id);
        return "Filme deletado com sucesso!";
    }

    @GetMapping("/buscar-filmes")
    public List<FilmeExternoDTO> buscarFilmesExternos(@RequestParam String query) {
        return filmesService.buscarFilmesAPIExterna(query);
    }
}