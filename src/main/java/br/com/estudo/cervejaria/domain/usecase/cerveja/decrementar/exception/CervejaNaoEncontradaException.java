package br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.exception;

import br.com.estudo.cervejaria.util.exception.NotFoundException;

public class CervejaNaoEncontradaException extends NotFoundException {
    public CervejaNaoEncontradaException() {
        super("Cerveja não encontrada.");
    }
}
