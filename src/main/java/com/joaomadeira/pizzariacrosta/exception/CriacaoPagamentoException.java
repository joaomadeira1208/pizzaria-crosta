package com.joaomadeira.pizzariacrosta.exception;

public class CriacaoPagamentoException extends RuntimeException{
    public CriacaoPagamentoException(String message) {
        super(message);
    }

    public CriacaoPagamentoException(String message, Throwable cause) {
        super(message, cause);
    }
}
