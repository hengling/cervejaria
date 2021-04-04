package br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.exception;

import br.com.estudo.cervejaria.util.exception.BusinessErrorException;

public class EstoqueNegativoException extends BusinessErrorException {
    public EstoqueNegativoException() {
        super("O estoque não pode ficar negativo.");
    }
}
