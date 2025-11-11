package com.seuprojeto.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmesController {

    @GetMapping
    public String getAllFilmes() {
        System.out.println("GET /filmes chamado");
        return "Listando todos os filmes...";
    }

    @GetMapping("/{id}")
    public String getFilmeById(@PathVariable Long id) {
        System.out.println("GET /filmes/" + id + " chamado");
        return "Buscando filme de ID " + id;
    }

    @PostMapping
    public String createFilme() {
        System.out.println("POST /filmes chamado");
        return "Novo filme cadastrado (teste)";
    }

    @PutMapping
    public String updateFilme() {
        System.out.println("PUT /filmes chamado");
        return "Filme atualizado (teste)";
    }

    @DeleteMapping("/{id}")
    public String deleteFilme(@PathVariable Long id) {
        System.out.println("DELETE /filmes/" + id + " chamado");
        return "Filme de ID " + id + " deletado (teste)";
    }

    @GetMapping("/buscar-filmes")
    public String buscarFilmesExternos(@RequestParam String query) {
        System.out.println("GET /buscar-filmes?query=" + query + " chamado");
        return "Buscando filme externo com query: " + query;
    }
}
