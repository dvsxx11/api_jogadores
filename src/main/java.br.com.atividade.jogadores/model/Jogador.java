package br.com.atividade.jogadores.model;

public class Jogador {

    private Long id;
    private String nome;
    private String posicao;
    private int idade;
    private int quantidadeGols;
    private int quantidadePartidas;
    private boolean ativo;

    public Jogador() {
    }

    public Jogador(Long id, String nome, String posicao, int idade,
                   int quantidadeGols, int quantidadePartidas, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.posicao = posicao;
        this.idade = idade;
        this.quantidadeGols = quantidadeGols;
        this.quantidadePartidas = quantidadePartidas;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getQuantidadeGols() {
        return quantidadeGols;
    }

    public void setQuantidadeGols(int quantidadeGols) {
        this.quantidadeGols = quantidadeGols;
    }

    public int getQuantidadePartidas() {
        return quantidadePartidas;
    }

    public void setQuantidadePartidas(int quantidadePartidas) {
        this.quantidadePartidas = quantidadePartidas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}