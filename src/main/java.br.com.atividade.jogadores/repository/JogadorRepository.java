package br.com.atividade.jogadores.repository;

import br.com.atividade.jogadores.model.Jogador;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JogadorRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Jogador> MAPEADOR = (rs, linha) ->
            new Jogador(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getString("posicao"),
                    rs.getInt("idade"),
                    rs.getInt("quantidade_gols"),
                    rs.getInt("quantidade_partidas"),
                    rs.getBoolean("ativo")
            );

    public JogadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Jogador> listarTodos() {
        String sql = "SELECT * FROM jogador ORDER BY id";
        return jdbcTemplate.query(sql, MAPEADOR);
    }

    public Optional<Jogador> buscarPorId(Long id) {
        String sql = "SELECT * FROM jogador WHERE id = ?";

        return jdbcTemplate.query(sql, MAPEADOR, id)
                .stream()
                .findFirst();
    }
}