package com.example.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repositories.AvaliacaoRepository;
import com.example.backend.repositories.FilmeRepository;
import com.example.backend.repositories.UsuarioRepository;
import com.example.backend.entidades.AvaliacaoEntidade;
import com.example.backend.entidades.FilmeEntidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository; // Precisa para atualizar a média

    @Autowired
    private UsuarioRepository usuarioRepository; // Precisa para validar o usuário

    // Retornar avaliações de um filme (GET /avaliacao/:filmeId)
    public List<AvaliacaoEntidade> getAvaliacoesByFilmeId(UUID filmeId) {
        // Verifica se o filme existe
        filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com id: " + filmeId));

        return avaliacaoRepository.findByFilmeId(filmeId);
    }

    // Retornar uma avaliação específica (GET /avaliacao/:id)
    public AvaliacaoEntidade getAvaliacaoById(UUID id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada com id: " + id));
    }

    // Cadastrar nova avaliação (POST /avaliacao)
    // Nota: A rota POST na sua tabela está estranha. O padrão é enviar
    // os IDs (filme_id, usuario_id) no corpo do JSON, e não na URL.
    // Este método segue o padrão de receber os dados no objeto 'avaliacao'.
    public AvaliacaoEntidade createAvaliacao(AvaliacaoEntidade avaliacao) {
        // Valida se o filme e o usuário existem
        UUID filmeId = avaliacao.getFilme().getId();
        UUID usuarioId = avaliacao.getUsuario().getId();

        filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com id: " + filmeId));

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + usuarioId));

        // Salva a avaliação
        AvaliacaoEntidade novaAvaliacao = avaliacaoRepository.save(avaliacao);

        // **Regra de Negócio: Atualiza a média do filme**
        atualizarMediaFilme(filmeId);

        return novaAvaliacao;
    }

    // Atualizar uma avaliação (UPDATE /avaliacao/:id)
    public AvaliacaoEntidade updateAvaliacao(UUID id, AvaliacaoEntidade avaliacaoDetails) {
        AvaliacaoEntidade existente = getAvaliacaoById(id);

        existente.setNota(avaliacaoDetails.getNota());
        existente.setComentario(avaliacaoDetails.getComentario());
        // Não se deve permitir alterar o filme_id ou usuario_id

        AvaliacaoEntidade avaliacaoAtualizada = avaliacaoRepository.save(existente);

        // **Regra de Negócio: Atualiza a média do filme**
        atualizarMediaFilme(existente.getFilme().getId());

        return avaliacaoAtualizada;
    }

    // Deletar uma avaliação (DELETE /avaliacao/:id)
    public void deleteAvaliacao(UUID id) {
        AvaliacaoEntidade avaliacao = getAvaliacaoById(id);
        UUID filmeId = avaliacao.getFilme().getId(); // Salva o ID do filme antes de deletar

        avaliacaoRepository.delete(avaliacao);

        // **Regra de Negócio: Atualiza a média do filme**
        atualizarMediaFilme(filmeId);
    }


    // --- MÉTODO PRIVADO (LÓGICA DE NEGÓCIO) ---

    // Este método é a "mágica" do seu service.
    // Ele é chamado toda vez que uma avaliação é criada, alterada ou removida.
    private void atualizarMediaFilme(UUID filmeId) {
        // 1. Busca o filme
        FilmeEntidade filme = filmeRepository.findById(filmeId).get();

        // 2. Busca todas as avaliações desse filme
        List<AvaliacaoEntidade> avaliacoes = avaliacaoRepository.findByFilmeId(filmeId);

        if (avaliacoes.isEmpty()) {
            filme.setNotaMedia(null); // Ou BigDecimal.ZERO, você decide
        } else {
            // 3. Calcula a média
            double media = avaliacoes.stream()
                    .mapToInt(AvaliacaoEntidade::getNota)
                    .average()
                    .orElse(0.0);

            // 4. Converte para BigDecimal (decimal(3,2) como no seu BD)
            BigDecimal mediaBd = BigDecimal.valueOf(media).setScale(2, RoundingMode.HALF_UP);
            filme.setNotaMedia(mediaBd);
        }

        // 5. Salva o filme com a nova média
        filmeRepository.save(filme);
    }
}