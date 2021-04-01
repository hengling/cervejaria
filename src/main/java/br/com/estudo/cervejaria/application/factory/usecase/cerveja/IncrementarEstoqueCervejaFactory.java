package br.com.estudo.cervejaria.application.factory.usecase.cerveja;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaUseCaseImpl;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.mapper.IncrementarEstoqueCervejaOutputDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class IncrementarEstoqueCervejaFactory {

    private final CervejaDataProvider cervejaDataProvider;

    private final IncrementarEstoqueCervejaOutputDataMapper outputDataMapper;

    @Bean("IncrementarEstoqueCervejaUseCase")
    public IncrementarEstoqueCervejaUseCase createUseCase() {
        return new IncrementarEstoqueCervejaUseCaseImpl(cervejaDataProvider, outputDataMapper);
    }
}
