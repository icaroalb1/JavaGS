package com.plataformaVerde.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.plataformaVerde.modelo.PontoRecarga;

public class PontoRecargaDAO {
    private Connection conexao;

    public PontoRecargaDAO() {
        this.conexao = FabricaConexao.getConexao();
    }

    public void inserir(PontoRecarga ponto) throws SQLException {
        String sql = "INSERT INTO ponto_recarga (localizacao, capacidade) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, ponto.getLocalizacao());
            stmt.setInt(2, ponto.getCapacidade());
            stmt.executeUpdate();
        }
    }

    public List<PontoRecarga> listar() throws SQLException {
        String sql = "SELECT * FROM ponto_recarga";
        List<PontoRecarga> pontos = new ArrayList<>();
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PontoRecarga ponto = new PontoRecarga(
                        rs.getInt("id"),
                        rs.getString("localizacao"),
                        rs.getInt("capacidade")
                );
                pontos.add(ponto);
            }
        }
        return pontos;
    }

    public void atualizar(PontoRecarga ponto) throws SQLException {
        String sql = "UPDATE ponto_recarga SET localizacao = ?, capacidade = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, ponto.getLocalizacao());
            stmt.setInt(2, ponto.getCapacidade());
            stmt.setInt(3, ponto.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ponto_recarga WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
