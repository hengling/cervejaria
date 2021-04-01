package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception.QuantidadeEmEstoqueExcedidaException;
import br.com.estudo.cervejaria.entrypoint.GlobalErrorHandler;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class IncrementarEstoqueCervejaControllerTest {

    private static final String URL_PATH = "/api/v1/cervejas";
    private static final Gson GSON = new Gson();

    private MockMvc mockMvc;

    @Mock
    private IncrementarEstoqueCervejaUseCase useCase;

    @InjectMocks
    private IncrementarEstoqueCervejaController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    public void quandoRealizarPATCHComOsDadosCorretosEntaoDeveIncrementarEstoqueDaCerveja() throws Exception {
        // given
        final IncrementarEstoqueCervejaInputData inputData = IncrementarEstoqueCervejaInputData.builder()
                .quantidade(10)
                .build();

        final IncrementarEstoqueCervejaOutputData outputData = IncrementarEstoqueCervejaOutputData.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo("LAGER")
                .build();

        // when
        when(useCase.execute(any(IncrementarEstoqueCervejaInputData.class))).thenReturn(outputData);

        // then
        var responseBodyString = mockMvc.perform(patch(URL_PATH + "/1/incremento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseBody = GSON.fromJson(responseBodyString, IncrementarEstoqueCervejaOutputData.class);

        assertThat(outputData, is(equalTo(responseBody)));
    }

    @Test
    public void quandoRealizarPATCHComUmIdInvalidoEntaoDeveRetornarErro() throws Exception {
        // given
        final IncrementarEstoqueCervejaInputData inputData = IncrementarEstoqueCervejaInputData.builder()
                .quantidade(10)
                .build();

        // when
        when(useCase.execute(any(IncrementarEstoqueCervejaInputData.class))).thenThrow(CervejaNaoEncontradaException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL_PATH + "/1/incremento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void quandoRealizarPATCHEQuantidadeExcederLimiteMaximoEntaoDeveRetornarErro() throws Exception {
        // given
        final IncrementarEstoqueCervejaInputData inputData = IncrementarEstoqueCervejaInputData.builder()
                .quantidade(10)
                .build();

        // when
        when(useCase.execute(any(IncrementarEstoqueCervejaInputData.class))).thenThrow(QuantidadeEmEstoqueExcedidaException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL_PATH + "/1/incremento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isInternalServerError());
    }
}
