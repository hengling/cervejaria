package br.com.estudo.cervejaria.domain.usecase.cerveja.remover;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.exception.CervejaNaoEncontradaException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoverCervejaUseCaseImpl implements RemoverCervejaUseCase {

    private final CervejaDataProvider cervejaDataProvider;

    @Override
    public void execute(RemoverCervejaInputData inputData) {
        final var cerveja = buscarCerveja(inputData);
        cervejaDataProvider.remover(cerveja);
    }

    private Cerveja buscarCerveja(RemoverCervejaInputData inputData) {
        return cervejaDataProvider.buscarPorId(inputData.getId())
                .orElseThrow(CervejaNaoEncontradaException::new);
    }
}
