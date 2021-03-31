package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.exception;

public class CervejaNaoEncontradaException extends RuntimeException {
    public CervejaNaoEncontradaException() {
        super("Cerveja não encontrada.");
    }
}
