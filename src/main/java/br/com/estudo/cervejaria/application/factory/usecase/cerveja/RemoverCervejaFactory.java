package br.com.estudo.cervejaria.application.factory.usecase.cerveja;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.RemoverCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.RemoverCervejaUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RemoverCervejaFactory {

    private final CervejaDataProvider cervejaDataProvider;

    @Bean("RemoverCervejaUseCase")
    public RemoverCervejaUseCase createUseCase() {
        return new RemoverCervejaUseCaseImpl(cervejaDataProvider);
    }
}
