package br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception;

import br.com.estudo.cervejaria.util.exception.BusinessErrorException;

public class QuantidadeEmEstoqueExcedidaException extends BusinessErrorException {
    public QuantidadeEmEstoqueExcedidaException() {
        super("A quantidade máxima de cerveja em estoque foi atingida.");
    }
}
