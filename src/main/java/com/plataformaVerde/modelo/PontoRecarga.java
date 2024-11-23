package com.plataformaVerde.modelo;

public class PontoRecarga {
    private int id;
    private String localizacao;
    private int capacidade;

    // Construtor padrão
    public PontoRecarga() {}

    // Construtor parametrizado
    public PontoRecarga(int id, String localizacao, int capacidade) {
        this.id = id;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    // Método adicional: Verificar disponibilidade
    public boolean possuiCapacidadeDisponivel(int veiculosConectados) {
        return veiculosConectados < capacidade;
    }

    // Método adicional: Exibir detalhes do ponto
    public String exibirDetalhes() {
        return "Ponto de Recarga em " + localizacao + " com capacidade para " + capacidade + " veículos.";
    }
}
