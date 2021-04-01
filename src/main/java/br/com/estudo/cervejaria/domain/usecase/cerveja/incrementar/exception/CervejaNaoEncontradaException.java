package br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception;

import br.com.estudo.cervejaria.util.exception.NotFoundException;

public class CervejaNaoEncontradaException extends NotFoundException {
    public CervejaNaoEncontradaException() {
        super("Cerveja não encontrada.");
    }
}
