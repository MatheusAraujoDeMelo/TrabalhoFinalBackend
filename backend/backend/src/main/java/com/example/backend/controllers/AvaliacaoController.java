package com.example.backend.controllers; // Garanta que o pacote está correto

import com.example.backend.entidades.AvaliacaoEntidade;
import com.example.backend.services.AvaliacaoService; // Importa seu service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID; // Importa o UUID

@RestController
@RequestMapping("/avaliacao") // Rota base
public class AvaliacaoController {

    // 1. Injeta o Service que você criou
    @Autowired
    private AvaliacaoService avaliacaoService;

    // Rota: GET /avaliacao/filme/{filmeId}
    // (Melhorei a rota que você tinha /avaliacao/{filmeId} para não dar conflito)
    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<AvaliacaoEntidade>> getAvaliacoesByFilmeId(@PathVariable UUID filmeId) {
        List<AvaliacaoEntidade> avaliacoes = avaliacaoService.getAvaliacoesByFilmeId(filmeId);
        return ResponseEntity.ok(avaliacoes);
    }

    // Rota: GET /avaliacao/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoEntidade> getAvaliacaoById(@PathVariable UUID id) {
        AvaliacaoEntidade avaliacao = avaliacaoService.getAvaliacaoById(id);
        return ResponseEntity.ok(avaliacao);
    }

    // Rota: POST /avaliacao
    // 2. É ESTA ROTA QUE VOCÊ ESTÁ TESTANDO!
    //    Ela recebe o JSON do Body com @RequestBody
    @PostMapping
    public ResponseEntity<AvaliacaoEntidade> createAvaliacao(@RequestBody AvaliacaoEntidade avaliacao) {
        // 3. Manda o objeto para o service
        AvaliacaoEntidade novaAvaliacao = avaliacaoService.createAvaliacao(avaliacao);

        // 4. Retorna 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacao);
    }

    // Rota: PUT /avaliacao/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoEntidade> updateAvaliacao(@PathVariable UUID id, @RequestBody AvaliacaoEntidade avaliacaoDetails) {
        AvaliacaoEntidade avaliacaoAtualizada = avaliacaoService.updateAvaliacao(id, avaliacaoDetails);
        return ResponseEntity.ok(avaliacaoAtualizada);
    }

    // Rota: DELETE /avaliacao/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable UUID id) {
        avaliacaoService.deleteAvaliacao(id);
        return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
    }
}