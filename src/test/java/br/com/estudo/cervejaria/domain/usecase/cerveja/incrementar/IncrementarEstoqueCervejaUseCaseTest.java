package br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception.QuantidadeEmEstoqueExcedidaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.mapper.IncrementarEstoqueCervejaOutputDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncrementarEstoqueCervejaUseCaseTest {
    private final IncrementarEstoqueCervejaOutputDataMapper outputDataMapper = IncrementarEstoqueCervejaOutputDataMapper.INSTANCE;

    final IncrementarEstoqueCervejaInputData inputData = IncrementarEstoqueCervejaInputData.builder()
            .id(1L)
            .quantidade(10)
            .build();

    @Mock
    private CervejaDataProvider cervejaDataProvider;

    private IncrementarEstoqueCervejaUseCase useCase;

    @BeforeEach
    void inicializarUseCase() {
        useCase = new IncrementarEstoqueCervejaUseCaseImpl(cervejaDataProvider, outputDataMapper);
    }

    @Test
    public void quandoInformarDadosCorretamenteDeveIncrementarOEstoque() {
        // given
        final Cerveja cerveja = Cerveja.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo(Cerveja.Tipo.LAGER)
                .build();

        final Cerveja cervejaAtualizada = Cerveja.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(cerveja.getQuantidade() + inputData.getQuantidade())
                .tipo(Cerveja.Tipo.LAGER)
                .build();

        final var outputDataEsperado = IncrementarEstoqueCervejaOutputData.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(cervejaAtualizada.getQuantidade())
                .tipo("LAGER")
                .build();

        // when
        when(cervejaDataProvider.buscarPorId(anyLong())).thenReturn(Optional.of(cerveja));
        when(cervejaDataProvider.salvar(any(Cerveja.class))).thenReturn(cervejaAtualizada);

        // then
        var outputData = useCase.execute(inputData);

        assertThat(outputDataEsperado, is(equalTo(outputData)));
        verify(cervejaDataProvider, times(1)).buscarPorId(inputData.getId());
        verify(cervejaDataProvider, times(1)).salvar(cervejaAtualizada);
    }

    @Test
    public void quandoCervejaNaoForEncontradaDeveLancarErro() {
        // when
        when(cervejaDataProvider.buscarPorId(anyLong())).thenReturn(Optional.empty());

        // then
        assertThrows(CervejaNaoEncontradaException.class, () -> useCase.execute(inputData));
    }

    @Test
    public void quandoQuantidadeExcederLimiteMaximoDeveLancarErro() {
        // given
        final Cerveja cerveja = Cerveja.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(50)
                .tipo(Cerveja.Tipo.LAGER)
                .build();

        // when
        when(cervejaDataProvider.buscarPorId(anyLong())).thenReturn(Optional.of(cerveja));

        // then
        assertThrows(QuantidadeEmEstoqueExcedidaException.class, () -> useCase.execute(inputData));
    }
}
