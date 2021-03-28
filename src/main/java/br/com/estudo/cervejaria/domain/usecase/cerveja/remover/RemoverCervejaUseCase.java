package br.com.estudo.cervejaria.domain.usecase.cerveja.remover;

@FunctionalInterface
public interface RemoverCervejaUseCase {

    void execute(RemoverCervejaInputData inputData);

}
