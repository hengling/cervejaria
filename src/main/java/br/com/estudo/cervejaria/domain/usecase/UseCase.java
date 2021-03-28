package br.com.estudo.cervejaria.domain.usecase;

@FunctionalInterface
public interface UseCase<INPUT, OUTPUT> {
    OUTPUT execute(INPUT inputData);
}
