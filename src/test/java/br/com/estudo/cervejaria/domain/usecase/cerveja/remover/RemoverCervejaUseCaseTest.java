package br.com.estudo.cervejaria.domain.usecase.cerveja.remover;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.exception.CervejaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoverCervejaUseCaseTest {

    @Mock
    private CervejaDataProvider cervejaDataProvider;

    private RemoverCervejaUseCase useCase;

    @BeforeEach
    void inicializarUseCase() {
        useCase = new RemoverCervejaUseCaseImpl(cervejaDataProvider);
    }

    @Test
    void quandoInformarUmaCervejaExistenteDeveRemoverACerveja() {
        final var cervejaId = 1L;
        final var inputData = new RemoverCervejaInputData(cervejaId);
        final var cervejaParaRemover = Cerveja.builder()
                .id(cervejaId)
                .build();

        when(cervejaDataProvider.buscarPorId(cervejaId)).thenReturn(Optional.of(cervejaParaRemover));

        useCase.execute(inputData);

        verify(cervejaDataProvider, times(1)).buscarPorId(cervejaId);
        verify(cervejaDataProvider, times(1)).remover(cervejaParaRemover);
    }

    @Test
    void quandoInformarUmaCervejaInexistenteDeveLancarExcecao() {
        final var cervejaId = 1L;
        final var inputData = new RemoverCervejaInputData(cervejaId);

        when(cervejaDataProvider.buscarPorId(cervejaId)).thenReturn(Optional.empty());

        assertThrows(CervejaNaoEncontradaException.class, () -> useCase.execute(inputData));
    }
}
