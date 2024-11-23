package com.plataformaVerde.excecao;

public class ExcecaoPersonalizada extends RuntimeException {
    public ExcecaoPersonalizada(String mensagem) {
        super(mensagem);
    }
}
