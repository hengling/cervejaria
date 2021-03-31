package br.com.estudo.cervejaria.domain.usecase.cerveja.remover.exception;

public class CervejaNaoEncontradaException extends RuntimeException {
    public CervejaNaoEncontradaException() {
        super("Cerveja não encontrada.");
    }
}
