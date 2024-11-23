package com.plataformaVerde.bo;

import java.sql.SQLException;
import java.util.List;

import com.plataformaVerde.dao.PontoRecargaDAO;
import com.plataformaVerde.modelo.PontoRecarga;

/**
 * Classe responsável pela lógica de negócio dos Pontos de Recarga.
 */
public class PontoRecargaBO {

    private PontoRecargaDAO dao;

    public PontoRecargaBO() {
        this.dao = new PontoRecargaDAO();
    }

    /**
     * Adiciona um novo ponto de recarga.
     * @param ponto O objeto PontoRecarga a ser adicionado.
     * @throws SQLException Se houver um erro ao inserir no banco de dados.
     */
    public void adicionarPonto(PontoRecarga ponto) throws SQLException {
        if (ponto == null || ponto.getLocalizacao() == null || ponto.getLocalizacao().isEmpty()) {
            throw new IllegalArgumentException("Localização do ponto não pode ser vazia.");
        }
        if (ponto.getCapacidade() <= 0) {
            throw new IllegalArgumentException("Capacidade do ponto deve ser maior que zero.");
        }
        dao.inserir(ponto);
    }

    /**
     * Lista todos os pontos de recarga cadastrados.
     * @return Lista de Pontos de Recarga.
     * @throws SQLException Se houver erro ao acessar o banco de dados.
     */
    public List<PontoRecarga> listarPontos() throws SQLException {
        return dao.listar();
    }

    /**
     * Atualiza um ponto de recarga existente.
     * @param ponto Objeto PontoRecarga atualizado.
     * @throws SQLException Se houver erro ao atualizar no banco de dados.
     */
    public void atualizarPonto(PontoRecarga ponto) throws SQLException {
        if (ponto == null || ponto.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido para atualização.");
        }
        dao.atualizar(ponto);
    }

    /**
     * Exclui um ponto de recarga pelo ID.
     * @param id ID do ponto a ser excluído.
     * @throws SQLException Se houver erro ao excluir no banco de dados.
     */
    public void excluirPonto(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        dao.excluir(id);
    }
}
