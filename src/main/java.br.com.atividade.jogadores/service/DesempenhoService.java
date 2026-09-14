package br.com.atividade.jogadores.service;

import br.com.atividade.jogadores.model.Jogador;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DesempenhoService {

    public Map<String, Object> calcularDesempenho(Jogador jogador) {
        Map<String, Object> desempenho = new LinkedHashMap<>();

        desempenho.put("id", jogador.getId());
        desempenho.put("nome", jogador.getNome());
        desempenho.put(
                "mediaGolsPorPartida",
                calcularMediaGolsPorPartida(jogador)
        );
        desempenho.put("classificacao", classificar(jogador));
        desempenho.put(
                "aptoParaSerTitular",
                estaAptoParaSerTitular(jogador)
        );

        return desempenho;
    }

    private double calcularMediaGolsPorPartida(Jogador jogador) {
        if (jogador.getQuantidadePartidas() == 0) {
            return 0.0;
        }

        return (double) jogador.getQuantidadeGols()
                / jogador.getQuantidadePartidas();
    }

    private String classificar(Jogador jogador) {
        if (jogador.getQuantidadePartidas() == 0) {
            return "Sem partidas suficientes";
        }

        double media = calcularMediaGolsPorPartida(jogador);

        if (media >= 0.5) {
            return "Excelente";
        }

        if (media >= 0.2) {
            return "Bom";
        }

        return "Regular";
    }

    private boolean estaAptoParaSerTitular(Jogador jogador) {
        return jogador.isAtivo()
                && jogador.getQuantidadePartidas() >= 5;
    }
}