package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista;

import br.com.estudo.cervejaria.domain.usecase.UseCase;

@FunctionalInterface
public interface BuscarListaCervejaUseCase {

    BuscarListaCervejaOutputData execute();

}
