package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repositories.UsuarioRepository;
import com.example.backend.entidades.UsuarioEntidade; // Assumindo este nome

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retornar todos os usuários (GET /usuarios)
    public List<UsuarioEntidade> getAllUsuarios() {
        List<UsuarioEntidade> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RuntimeException("Nenhum usuário encontrado.");
        }
        return usuarios;
    }

    // Retornar um usuário por id (GET /usuarios/:id)
    public UsuarioEntidade getUsuarioById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    // Cadastrar novo usuário (POST /usuarios)
    public UsuarioEntidade createUsuario(UsuarioEntidade usuario) {
        // Adicionando validações, similar ao seu createFilme
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new RuntimeException("Nome de usuário inválido.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RuntimeException("Email de usuário inválido.");
        }
        // Poderia adicionar validação de email já existente aqui
        return usuarioRepository.save(usuario);
    }

    // Atualizar um usuário (UPDATE /usuarios)
    // Nota: Seguindo o seu padrão, o ID virá no objeto 'usuario'
    public UsuarioEntidade updateUsuario(UsuarioEntidade usuario) {
        // Primeiro, busca o usuário existente
        UsuarioEntidade existente = getUsuarioById(usuario.getId());

        // Atualiza os campos
        existente.setNome(usuario.getNome());
        existente.setEmail(usuario.getEmail());
        // Se tiver outros campos (ex: senha), atualize aqui

        return usuarioRepository.save(existente);
    }

    // Deletar um usuário (DELETE /usuarios/:id)
    public void deleteUsuario(UUID id) {
        UsuarioEntidade usuario = getUsuarioById(id);
        usuarioRepository.delete(usuario);
    }
}