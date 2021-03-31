package br.com.estudo.cervejaria.application.factory.usecase.cerveja;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaUseCaseImpl;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.mapper.InserirCervejaOutputDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InserirCervejaFactory {

    private final CervejaDataProvider cervejaDataProvider;

    private final InserirCervejaOutputDataMapper outputDataMapper;

    @Bean("InserirCervejaUseCase")
    public InserirCervejaUseCase createUseCase() {
        return new InserirCervejaUseCaseImpl(cervejaDataProvider, outputDataMapper);
    }
}
