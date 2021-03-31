package br.com.estudo.cervejaria.application.factory.usecase.cerveja;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeUseCaseImpl;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.mapper.BuscarCervejaPorNomeOutputDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BuscarCervejaPorNomeFactory {

    private final CervejaDataProvider cervejaDataProvider;

    private final BuscarCervejaPorNomeOutputDataMapper outputDataMapper;

    @Bean("BuscarCervejaPorNomeUseCase")
    public BuscarCervejaPorNomeUseCase createUseCase() {
        return new BuscarCervejaPorNomeUseCaseImpl(
                cervejaDataProvider,
                outputDataMapper
        );
    }
}
