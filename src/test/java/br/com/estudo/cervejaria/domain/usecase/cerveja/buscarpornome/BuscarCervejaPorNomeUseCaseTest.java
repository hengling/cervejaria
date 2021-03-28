package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.mapper.BuscarCervejaPorNomeOutputDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarCervejaPorNomeUseCaseTest {

    private final BuscarCervejaPorNomeOutputDataMapper outputDataMapper = BuscarCervejaPorNomeOutputDataMapper.INSTANCE;

    @Mock
    private CervejaDataProvider cervejaDataProvider;

    private BuscarCervejaPorNomeUseCase useCase;

    @BeforeEach
    void inicializarUseCase() {
        useCase = new BuscarCervejaPorNomeUseCaseImpl(
                cervejaDataProvider,
                outputDataMapper
        );
    }

    @Test
    void quandoInformarUmNomeDeCervejaValidoDeveRetornarACerveja() {
        final var nome = "Brahma";

        final Cerveja cerveja = Cerveja.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo(Cerveja.Tipo.LAGER)
                .build();

        final var outputDataEsperado = BuscarCervejaPorNomeOutputData.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo(Cerveja.Tipo.LAGER.name())
                .build();

        when(cervejaDataProvider.buscarPorNome(nome)).thenReturn(Optional.of(cerveja));

        final var outputData = useCase.execute(BuscarCervejaPorNomeInputData.builder()
                .nome(nome)
                .build());

        assertThat(outputDataEsperado, is(equalTo(outputData)));
    }

    @Test
    void quandoInformarUmNomeDeCervejaInvalidoDeveFalharELancarExcecao() {
        when(cervejaDataProvider.buscarPorNome(anyString())).thenReturn(Optional.empty());

        assertThrows(CervejaNaoEncontradaException.class, () -> useCase.execute(BuscarCervejaPorNomeInputData
                .builder()
                .nome("Brahma")
                .build()));
    }
}
