package br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.exception;

public class CervejaJaCadastradaException extends RuntimeException {
    public CervejaJaCadastradaException() {
        super("A cerveja já existe.");
    }
}
