package com.plataformaVerde.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.plataformaVerde.modelo.Simulacao;

public class SimulacaoDAO {
    private Connection conexao;

    public SimulacaoDAO() {
        this.conexao = FabricaConexao.getConexao();
    }

    public void inserir(Simulacao simulacao) throws SQLException {
        String sql = "INSERT INTO simulacao (veiculo_atual, consumo_atual, custo_manutencao, custo_combustivel, economia_estimada) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, simulacao.getVeiculoAtual());
            stmt.setDouble(2, simulacao.getConsumoAtual());
            stmt.setDouble(3, simulacao.getCustoManutencao());
            stmt.setDouble(4, simulacao.getCustoCombustivel());
            stmt.setDouble(5, simulacao.getEconomiaEstimada());
            stmt.executeUpdate();
        }
    }

    public List<Simulacao> listar() throws SQLException {
        String sql = "SELECT * FROM simulacao";
        List<Simulacao> simulacoes = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Simulacao simulacao = new Simulacao(
                        rs.getInt("id"),
                        rs.getString("veiculo_atual"),
                        rs.getDouble("consumo_atual"),
                        rs.getDouble("custo_manutencao"),
                        rs.getDouble("custo_combustivel"),
                        rs.getDouble("economia_estimada")
                );
                simulacoes.add(simulacao);
            }
        }
        return simulacoes;
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM simulacao WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
