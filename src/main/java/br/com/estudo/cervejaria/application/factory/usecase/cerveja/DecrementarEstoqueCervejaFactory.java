package br.com.estudo.cervejaria.application.factory.usecase.cerveja;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaUseCaseImpl;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.mapper.DecrementarEstoqueCervejaOutputDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DecrementarEstoqueCervejaFactory {

    private final CervejaDataProvider cervejaDataProvider;

    private final DecrementarEstoqueCervejaOutputDataMapper outputDataMapper;

    @Bean("DecrementarEstoqueCervejaUseCase")
    public DecrementarEstoqueCervejaUseCase createUseCase() {
        return new DecrementarEstoqueCervejaUseCaseImpl(cervejaDataProvider, outputDataMapper);
    }
}
