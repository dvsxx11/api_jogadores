package br.com.atividade.jogadores.service;

import br.com.atividade.jogadores.model.Jogador;
import br.com.atividade.jogadores.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public List<Jogador> listarTodos() {
        return jogadorRepository.listarTodos();
    }

    public Optional<Jogador> buscarPorId(Long id) {
        return jogadorRepository.buscarPorId(id);
    }

    public List<Jogador> listarAtivos() {
        return jogadorRepository.listarTodos().stream()
                .filter(Jogador::isAtivo)
                .toList();
    }
}