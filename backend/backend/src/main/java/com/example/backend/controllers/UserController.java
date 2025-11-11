package com.seuprojeto.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @GetMapping
    public String getAllUsuarios() {
        System.out.println("GET /usuarios chamado");
        return "Listando todos os usuários...";
    }

    @GetMapping("/{id}")
    public String getUsuarioById(@PathVariable Long id) {
        System.out.println("GET /usuarios/" + id + " chamado");
        return "Buscando usuário ID " + id;
    }

    @PostMapping
    public String createUsuario() {
        System.out.println("POST /usuarios chamado");
        return "Novo usuário criado (teste)";
    }

    @PutMapping
    public String updateUsuario() {
        System.out.println("PUT /usuarios chamado");
        return "Usuário atualizado (teste)";
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Long id) {
        System.out.println("DELETE /usuarios/" + id + " chamado");
        return "Usuário ID " + id + " deletado (teste)";
    }
}
