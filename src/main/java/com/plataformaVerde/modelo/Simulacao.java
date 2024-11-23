package com.plataformaVerde.modelo;

public class Simulacao {
    private int id;
    private String veiculoAtual;
    private double consumoAtual;
    private double custoManutencao;
    private double custoCombustivel;
    private double economiaEstimada;

    // Construtor padrão
    public Simulacao() {}

    // Construtor parametrizado
    public Simulacao(int id, String veiculoAtual, double consumoAtual, double custoManutencao, double custoCombustivel, double economiaEstimada) {
        this.id = id;
        this.veiculoAtual = veiculoAtual;
        this.consumoAtual = consumoAtual;
        this.custoManutencao = custoManutencao;
        this.custoCombustivel = custoCombustivel;
        this.economiaEstimada = economiaEstimada;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVeiculoAtual() {
        return veiculoAtual;
    }

    public void setVeiculoAtual(String veiculoAtual) {
        this.veiculoAtual = veiculoAtual;
    }

    public double getConsumoAtual() {
        return consumoAtual;
    }

    public void setConsumoAtual(double consumoAtual) {
        this.consumoAtual = consumoAtual;
    }

    public double getCustoManutencao() {
        return custoManutencao;
    }

    public void setCustoManutencao(double custoManutencao) {
        this.custoManutencao = custoManutencao;
    }

    public double getCustoCombustivel() {
        return custoCombustivel;
    }

    public void setCustoCombustivel(double custoCombustivel) {
        this.custoCombustivel = custoCombustivel;
    }

    public double getEconomiaEstimada() {
        return economiaEstimada;
    }

    public void setEconomiaEstimada(double economiaEstimada) {
        this.economiaEstimada = economiaEstimada;
    }

    // Método adicional: Calcular custo total atual
    public double calcularCustoAtual() {
        return consumoAtual * custoCombustivel + custoManutencao;
    }

    // Método adicional: Descrever simulação
    public String descreverSimulacao() {
        return "Simulação para " + veiculoAtual + ": Economia estimada de R$ " + economiaEstimada;
    }
}
