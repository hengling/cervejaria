package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.mapper.BuscarCervejaPorNomeOutputDataMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscarCervejaPorNomeUseCaseImpl implements BuscarCervejaPorNomeUseCase {

    private CervejaDataProvider cervejaDataProvider;

    private BuscarCervejaPorNomeOutputDataMapper outputDataMapper;

    @Override
    public BuscarCervejaPorNomeOutputData execute(BuscarCervejaPorNomeInputData inputData) {
        final var cerveja = buscarCerveja(inputData);
        return outputDataMapper.toOutputData(cerveja);
    }

    private Cerveja buscarCerveja(BuscarCervejaPorNomeInputData inputData) {
        return cervejaDataProvider.buscarPorNome(inputData.getNome()).orElseThrow(CervejaNaoEncontradaException::new);
    }
}
