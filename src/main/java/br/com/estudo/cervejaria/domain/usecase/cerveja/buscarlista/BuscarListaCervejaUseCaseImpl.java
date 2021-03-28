package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.mapper.BuscarListaCervejaOutputDataMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuscarListaCervejaUseCaseImpl implements BuscarListaCervejaUseCase {

    private final CervejaDataProvider cervejaDataProvider;

    private final BuscarListaCervejaOutputDataMapper outputDataMapper;

    @Override
    public BuscarListaCervejaOutputData execute(BuscarListaCervejaInputData inputData) {
        return outputDataMapper.toOutputData(cervejaDataProvider.buscarTodos());
    }
}
