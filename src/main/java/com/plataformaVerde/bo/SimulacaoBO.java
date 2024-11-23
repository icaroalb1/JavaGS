package com.plataformaVerde.bo;

import java.sql.SQLException;
import java.util.List;

import com.plataformaVerde.dao.SimulacaoDAO;
import com.plataformaVerde.modelo.Simulacao;

/**
 * Classe responsável pela lógica de negócio das Simulações.
 */
public class SimulacaoBO {

    private SimulacaoDAO simulacaoDAO;

    public SimulacaoBO() {
        this.simulacaoDAO = new SimulacaoDAO();
    }

    /**
     * Adiciona uma nova simulação.
     * @param simulacao Objeto Simulacao a ser adicionado.
     * @throws SQLException Se houver erro ao inserir no banco de dados.
     */
    public void adicionarSimulacao(Simulacao simulacao) throws SQLException {
        if (simulacao == null || simulacao.getVeiculoAtual() == null || simulacao.getVeiculoAtual().isEmpty()) {
            throw new IllegalArgumentException("O veículo atual é obrigatório.");
        }
        if (simulacao.getConsumoAtual() <= 0 || simulacao.getCustoCombustivel() <= 0) {
            throw new IllegalArgumentException("Consumo e custo do combustível devem ser positivos.");
        }
        simulacao.setEconomiaEstimada(calcularEconomia(simulacao));
        simulacaoDAO.inserir(simulacao);
    }

    /**
     * Lista todas as simulações cadastradas.
     * @return Lista de Simulações.
     * @throws SQLException Se houver erro ao acessar o banco de dados.
     */
    public List<Simulacao> listarSimulacoes() throws SQLException {
        return simulacaoDAO.listar();
    }

    /**
     * Exclui uma simulação pelo ID.
     * @param id ID da simulação a ser excluída.
     * @throws SQLException Se houver erro ao excluir no banco de dados.
     */
    public void excluirSimulacao(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        simulacaoDAO.excluir(id);
    }

    /**
     * Calcula a economia estimada de uma simulação.
     * @param simulacao Objeto Simulacao contendo os dados para cálculo.
     * @return Economia estimada.
     */
    private double calcularEconomia(Simulacao simulacao) {
        // Exemplo simples de cálculo de economia
        double custoAtual = simulacao.getConsumoAtual() * simulacao.getCustoCombustivel() + simulacao.getCustoManutencao();
        double custoEstimado = simulacao.getConsumoAtual() * 0.5 * simulacao.getCustoCombustivel(); // Consumo reduzido em 50%
        return custoAtual - custoEstimado;
    }
}
