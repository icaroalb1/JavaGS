package com.plataformaVerde.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    // Configuração do Oracle
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl"; // Substitua pelo seu host, porta e SID
    private static final String USUARIO = "rm555131"; // Seu usuário
    private static final String SENHA = "040895";    // Sua senha

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados Oracle: " + e.getMessage());
        }
    }
}
