package br.com.atividade.jogadores.controller;

import br.com.atividade.jogadores.model.Jogador;
import br.com.atividade.jogadores.service.DesempenhoService;
import br.com.atividade.jogadores.service.JogadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jogadores")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<List<Jogador>> listarTodos() {
        return ResponseEntity.ok(jogadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable Long id) {
        return jogadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Jogador>> listarAtivos() {
        return ResponseEntity.ok(jogadorService.listarAtivos());
    }

    @GetMapping("/{id}/desempenho")
    public ResponseEntity<Map<String, Object>> buscarDesempenho(
            @PathVariable Long id
    ) {
        return jogadorService.buscarPorId(id)
                .map(desempenhoService::calcularDesempenho)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Jogador> cadastrar(
            @RequestBody Jogador jogador
    ) {
        Jogador cadastrado = jogadorService.cadastrar(jogador);

        URI location = URI.create("/jogadores/" + cadastrado.getId());

        return ResponseEntity
                .created(location)
                .body(cadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(
            @PathVariable Long id,
            @RequestBody Jogador jogador
    ) {
        return jogadorService.atualizar(id, jogador)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!jogadorService.apagar(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}