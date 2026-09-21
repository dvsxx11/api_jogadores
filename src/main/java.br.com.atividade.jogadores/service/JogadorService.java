package br.com.atividade.jogadores.service;

import br.com.atividade.jogadores.model.Jogador;
import br.com.atividade.jogadores.repository.JogadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return jogadorRepository.listarTodos()
                .stream()
                .filter(Jogador::isAtivo)
                .toList();
    }

    public Jogador cadastrar(Jogador jogador) {
        validar(jogador);
        return jogadorRepository.cadastrar(jogador);
    }

    public Optional<Jogador> atualizar(Long id, Jogador jogador) {
        validar(jogador);
        return jogadorRepository.atualizar(id, jogador);
    }

    public boolean apagar(Long id) {
        return jogadorRepository.apagar(id);
    }

    private void validar(Jogador jogador) {
        if (jogador == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O corpo da requisição é obrigatório."
            );
        }

        if (jogador.getNome() == null
                || jogador.getNome().isBlank()
                || jogador.getNome().length() > 100) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome é obrigatório e deve ter até 100 caracteres."
            );
        }

        if (jogador.getPosicao() == null
                || jogador.getPosicao().isBlank()
                || jogador.getPosicao().length() > 50) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Posição é obrigatória e deve ter até 50 caracteres."
            );
        }

        if (jogador.getClube() != null
                && jogador.getClube().length() > 150) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Clube deve ter até 150 caracteres."
            );
        }

        if (jogador.getIdade() < 0
                || jogador.getQuantidadeGols() < 0
                || jogador.getQuantidadePartidas() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Idade, gols e partidas não podem ser negativos."
            );
        }
    }
}