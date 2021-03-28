package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.mapper.BuscarListaCervejaOutputDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarListaCervejaUseCaseTest {

    private final BuscarListaCervejaOutputDataMapper outputDataMapper = BuscarListaCervejaOutputDataMapper.INSTANCE;

    @Mock
    private CervejaDataProvider cervejaDataProvider;

    private BuscarListaCervejaUseCase useCase;

    @BeforeEach
    void inicializarUseCase() {
        useCase = new BuscarListaCervejaUseCaseImpl(
                cervejaDataProvider,
                outputDataMapper
        );
    }

    @Test
    void quandoExistirCervejasCadastradasDeveRetornarAListaDeCervejas() {
        final var cerveja = Cerveja.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo(Cerveja.Tipo.LAGER)
                .build();

        final var outputDataEsperado = BuscarListaCervejaOutputData.builder()
                .itens(Collections.singletonList(BuscarListaCervejaOutputData.Item.builder()
                        .id(1L)
                        .nome("Brahma")
                        .marca("Ambev")
                        .max(50)
                        .quantidade(10)
                        .tipo(Cerveja.Tipo.LAGER.name())
                        .build()))
                .build();

        when(cervejaDataProvider.buscarTodos()).thenReturn(Collections.singletonList(cerveja));

        final var outputData = useCase.execute();

        assertThat(outputDataEsperado, is(equalTo(outputData)));
    }

    @Test
    void quandoNaoHouverCervejaCadastradaDeveRetornarAListaVazia() {
        final var outputDataEsperado = BuscarListaCervejaOutputData.builder()
                .itens(new ArrayList<>())
                .build();

        when(cervejaDataProvider.buscarTodos()).thenReturn(Collections.emptyList());

        final var outputData = useCase.execute();

        assertThat(outputDataEsperado, is(equalTo(outputData)));
    }
}
