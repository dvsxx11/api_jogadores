# API de Jogadores

API REST simples para consulta de jogadores de futebol, desenvolvida com Java, Spring Boot, Maven e Spring Web.

Os jogadores são armazenados em memória utilizando uma `List`, sem banco de dados.

## Funcionalidades

- Listar todos os jogadores;
- buscar jogador pelo ID;
- listar jogadores ativos;
- consultar o desempenho de um jogador;
- retornar `404 Not Found` quando o jogador não existe.

## Regras de negócio

Um jogador está apto para ser titular quando está ativo e possui pelo menos cinco partidas.

A classificação considera a média de gols por partida:

- média maior ou igual a `0,5`: Excelente;
- média maior ou igual a `0,2`: Bom;
- média menor que `0,2`: Regular;
- nenhuma partida: Sem partidas suficientes.

## Estrutura

```text
controller
└── JogadorController.java

model
└── Jogador.java

repository
└── JogadorRepository.java

service
├── JogadorService.java
└── DesempenhoService.java
```

## Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/jogadores` | Lista todos os jogadores |
| GET | `/jogadores/{id}` | Busca um jogador pelo ID |
| GET | `/jogadores/ativos` | Lista jogadores ativos |
| GET | `/jogadores/{id}/desempenho` | Consulta o desempenho |

## Como executar

No terminal, dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

## Testes

Exemplos para testar no Postman:

```text
GET http://localhost:8080/jogadores
GET http://localhost:8080/jogadores/1
GET http://localhost:8080/jogadores/ativos
GET http://localhost:8080/jogadores/1/desempenho
```

Para testar o retorno `404`:

```text
GET http://localhost:8080/jogadores/999
```

## Autor

Desenvolvido por Davi Silva Soares.