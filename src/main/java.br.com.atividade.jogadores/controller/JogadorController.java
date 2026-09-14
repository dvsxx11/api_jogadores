package br.com.atividade.jogadores.controller;

import br.com.atividade.jogadores.model.Jogador;
import br.com.atividade.jogadores.service.DesempenhoService;
import br.com.atividade.jogadores.service.JogadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;
    private final DesempenhoService desempenhoService;

    public JogadorController(
            JogadorService jogadorService,
            DesempenhoService desempenhoService
    ) {
        this.jogadorService = jogadorService;
        this.desempenhoService = desempenhoService;
    }

    @GetMapping
    public List<Jogador> listarTodos() {
        return jogadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(
            @PathVariable("id") Long id
    ) {
        return jogadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ativos")
    public List<Jogador> listarAtivos() {
        return jogadorService.listarAtivos();
    }

    @GetMapping("/{id}/desempenho")
    public ResponseEntity<Map<String, Object>> buscarDesempenho(
            @PathVariable("id") Long id
    ) {
        return jogadorService.buscarPorId(id)
                .map(desempenhoService::calcularDesempenho)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}