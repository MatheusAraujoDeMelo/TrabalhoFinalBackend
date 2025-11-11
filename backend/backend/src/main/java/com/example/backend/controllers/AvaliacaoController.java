package com.seuprojeto.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @GetMapping("/{filmeId}")
    public String getAvaliacoesByFilme(@PathVariable Long filmeId) {
        System.out.println("GET /avaliacao/" + filmeId + " chamado");
        return "Listando avaliações do filme ID " + filmeId;
    }

    @GetMapping("/detalhe/{id}")
    public String getAvaliacaoById(@PathVariable Long id) {
        System.out.println("GET /avaliacao/detalhe/" + id + " chamado");
        return "Buscando avaliação ID " + id;
    }

    @PostMapping("/{id}/{filmeId}")
    public String createAvaliacao(@PathVariable Long id, @PathVariable Long filmeId) {
        System.out.println("POST /avaliacao/" + id + "/" + filmeId + " chamado");
        return "Criando avaliação ID " + id + " para filme " + filmeId;
    }

    @PutMapping("/{id}")
    public String updateAvaliacao(@PathVariable Long id) {
        System.out.println("PUT /avaliacao/" + id + " chamado");
        return "Atualizando avaliação ID " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteAvaliacao(@PathVariable Long id) {
        System.out.println("DELETE /avaliacao/" + id + " chamado");
        return "Deletando avaliação ID " + id;
    }
}
