package br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.exception;

import br.com.estudo.cervejaria.util.exception.BusinessErrorException;

public class CervejaJaCadastradaException extends BusinessErrorException {
    public CervejaJaCadastradaException() {
        super("A cerveja já existe.");
    }
}
