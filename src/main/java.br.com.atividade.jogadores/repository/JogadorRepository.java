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

    private static final RowMapper<Jogador> MAPEADOR = (rs, linha) -> {
        Jogador jogador = new Jogador(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("posicao"),
                rs.getInt("idade"),
                rs.getInt("quantidade_gols"),
                rs.getInt("quantidade_partidas"),
                rs.getBoolean("ativo")
        );

        jogador.setClube(rs.getString("clube"));
        jogador.setNumeroCamisa(rs.getObject("numero_camisa", Integer.class));

        return jogador;
    };

    public JogadorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Jogador> listarTodos() {
        String sql = """
                SELECT *
                FROM jogador
                ORDER BY id
                """;

        return jdbcTemplate.query(sql, MAPEADOR);
    }

    public Optional<Jogador> buscarPorId(Long id) {
        String sql = """
                SELECT *
                FROM jogador
                WHERE id = ?
                """;

        return jdbcTemplate
                .query(sql, MAPEADOR, id)
                .stream()
                .findFirst();
    }

    public Jogador cadastrar(Jogador jogador) {
        String sql = """
                INSERT INTO jogador
                    (
                        nome,
                        posicao,
                        clube,
                        numero_camisa,
                        idade,
                        quantidade_gols,
                        quantidade_partidas,
                        ativo
                    )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                RETURNING *
                """;

        return jdbcTemplate.queryForObject(
                sql,
                MAPEADOR,
                jogador.getNome(),
                jogador.getPosicao(),
                jogador.getClube(),
                jogador.getNumeroCamisa(),
                jogador.getIdade(),
                jogador.getQuantidadeGols(),
                jogador.getQuantidadePartidas(),
                jogador.isAtivo()
        );
    }

    public Optional<Jogador> atualizar(Long id, Jogador jogador) {
        String sql = """
                UPDATE jogador
                SET
                    nome = ?,
                    posicao = ?,
                    clube = ?,
                    numero_camisa = ?,
                    idade = ?,
                    quantidade_gols = ?,
                    quantidade_partidas = ?,
                    ativo = ?
                WHERE id = ?
                RETURNING *
                """;

        return jdbcTemplate.query(
                sql,
                MAPEADOR,
                jogador.getNome(),
                jogador.getPosicao(),
                jogador.getClube(),
                jogador.getNumeroCamisa(),
                jogador.getIdade(),
                jogador.getQuantidadeGols(),
                jogador.getQuantidadePartidas(),
                jogador.isAtivo(),
                id
        ).stream().findFirst();
    }

    public boolean apagar(Long id) {
        String sql = """
                DELETE FROM jogador
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql, id) > 0;
    }
}
