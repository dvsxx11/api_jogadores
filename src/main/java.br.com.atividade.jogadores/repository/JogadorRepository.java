package br.com.atividade.jogadores.repository;

import br.com.atividade.jogadores.model.Jogador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.spi.ToolProvider.findFirst;

@Repository
public class JogadorRepository {

    private final List<Jogador> jogadores = new ArrayList<>();

    public JogadorRepository() {
        jogadores.add(new Jogador(
                1L, "Depay", "Atacante", 32, 32, 12, true));

        jogadores.add(new Jogador(
                2L, "Garro", "Meio-campista", 28, 3, 15, true));

        jogadores.add(new Jogador(
                3L, "Gabriel Paulista", "Zagueiro", 35, 1, 18, false));

        jogadores.add(new Jogador(
                4L, "Hugo Souza", "Goleiro", 27, 0, 0, true));

        jogadores.add(new Jogador(
                5L, "Yuri Alberto", "Atacante", 25, 60, 3, false));

    }

    public List<Jogador> listarTodos() {
        return new ArrayList<>(jogadores);
    }

    public Optional<Jogador> buscarPorId(Long id) {
        return jogadores.stream()
                .filter(jogador-> jogador.getId().equals(id))
                .findFirst();
    }
}