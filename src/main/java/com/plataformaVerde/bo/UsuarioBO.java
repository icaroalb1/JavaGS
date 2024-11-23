package com.plataformaVerde.bo;

import java.util.List;

import com.plataformaVerde.dao.UsuarioDAO;
import com.plataformaVerde.modelo.Usuario;

/**
 * Classe responsável pela lógica de negócio dos Usuários.
 */
public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Valida os dados do usuário.
     * @param usuario Objeto Usuario a ser validado.
     * @return True se os dados forem válidos, caso contrário false.
     */
    public boolean validarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null) {
            return false;
        }
        // Regex para validação mais completa do e-mail
        if (!usuario.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (usuario.getSenha().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
        return true;
    }

    /**
     * Salva um novo usuário no banco de dados.
     * @param usuario Objeto Usuario a ser salvo.
     * @throws Exception Se houver erro ao salvar no banco de dados.
     */
    public void salvarUsuario(Usuario usuario) throws Exception {
        if (!validarUsuario(usuario)) {
            throw new IllegalArgumentException("Dados do usuário inválidos.");
        }
        usuarioDAO.inserir(usuario);
    }

    /**
     * Lista todos os usuários cadastrados.
     * @return Lista de Usuários.
     * @throws Exception Se houver erro ao acessar o banco de dados.
     */
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioDAO.listar();
    }
}
