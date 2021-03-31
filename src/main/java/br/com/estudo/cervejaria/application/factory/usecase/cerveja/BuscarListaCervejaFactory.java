package br.com.estudo.cervejaria.application.factory.usecase.cerveja;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaUseCaseImpl;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.mapper.BuscarListaCervejaOutputDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BuscarListaCervejaFactory {

    private final CervejaDataProvider cervejaDataProvider;

    private final BuscarListaCervejaOutputDataMapper outputDataMapper;

    @Bean("BuscarListaCervejaUseCase")
    public BuscarListaCervejaUseCase createUseCase() {
        return new BuscarListaCervejaUseCaseImpl(cervejaDataProvider, outputDataMapper);
    }
}
