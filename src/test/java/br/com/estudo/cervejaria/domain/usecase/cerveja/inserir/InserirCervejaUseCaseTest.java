package br.com.estudo.cervejaria.domain.usecase.cerveja.inserir;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.exception.CervejaJaCadastradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.mapper.InserirCervejaOutputDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InserirCervejaUseCaseTest {

    private final InserirCervejaOutputDataMapper outputDataMapper = InserirCervejaOutputDataMapper.INSTANCE;

    final InserirCervejaInputData inputData = InserirCervejaInputData.builder()
            .nome("Brahma")
            .marca("Ambev")
            .max(50)
            .quantidade(10)
            .tipo(Cerveja.Tipo.LAGER)
            .build();

    final Cerveja cervejaSalva = Cerveja.builder()
            .id(1L)
            .nome("Brahma")
            .marca("Ambev")
            .max(50)
            .quantidade(10)
            .tipo(Cerveja.Tipo.LAGER)
            .build();

    final InserirCervejaOutputData outputDataEsperado = InserirCervejaOutputData.builder()
            .id(1L)
            .nome("Brahma")
            .marca("Ambev")
            .max(50)
            .quantidade(10)
            .tipo("LAGER")
            .build();

    @Mock
    private CervejaDataProvider cervejaDataProvider;

    private InserirCervejaUseCase useCase;

    @BeforeEach
    void inicializarUseCase() {
        useCase = new InserirCervejaUseCaseImpl(cervejaDataProvider, outputDataMapper);
    }

    @Test
    void quandoInformarOsDadosCorretamenteDeveInserirCervejaQ() {
        when(cervejaDataProvider.salvar(any(Cerveja.class))).thenReturn(cervejaSalva);

        var outputData = useCase.execute(inputData);

        assertThat(outputData, is(equalTo(outputDataEsperado)));
    }

    @Test
    void quandoJaExistirOutraCervejaComOMesmoNomeDeveLancarExcecao() {
        when(cervejaDataProvider.buscarPorNome(inputData.getNome())).thenReturn(Optional.of(cervejaSalva));

        assertThrows(CervejaJaCadastradaException.class, () -> useCase.execute(inputData));
    }
}
