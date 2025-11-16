package com.example.backend.controllers; // Pacote corrigido!

import com.example.backend.entidades.UsuarioEntidade;
import com.example.backend.services.UsuarioService; // Importe seu service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID; // Lembre-se que você usa UUID, não Long!

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // 1. Injeta o Service que você criou
    @Autowired
    private UsuarioService usuarioService; // Se o nome for UsuarioService, mude aqui

    // GET /usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioEntidade>> getAllUsuarios() {
        List<UsuarioEntidade> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // GET /usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntidade> getUsuarioById(@PathVariable UUID id) {
        UsuarioEntidade usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    // POST /usuarios
    // 2. Recebe o JSON do Postman com @RequestBody
    @PostMapping
    public ResponseEntity<UsuarioEntidade> createUsuario(@RequestBody UsuarioEntidade usuario) {
        // 3. Manda o objeto 'usuario' para o service salvar
        UsuarioEntidade novoUsuario = usuarioService.createUsuario(usuario);

        // 4. Retorna 201 Created com o usuário salvo no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // PUT /usuarios
    @PutMapping
    public ResponseEntity<UsuarioEntidade> updateUsuario(@RequestBody UsuarioEntidade usuario) {
        UsuarioEntidade usuarioAtualizado = usuarioService.updateUsuario(usuario);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    // DELETE /usuarios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
    }
}