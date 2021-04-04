package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.exception.EstoqueNegativoException;
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
public class DecrementarEstoqueCervejaControllerTest {

    private static final String URL_PATH = "/api/v1/cervejas";
    private static final Gson GSON = new Gson();

    private MockMvc mockMvc;

    @Mock
    private DecrementarEstoqueCervejaUseCase useCase;

    @InjectMocks
    private DecrementarEstoqueCervejaController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    public void quandoRealizarPATCHComOsDadosCorretosEntaoDeveDecrementarEstoqueDaCerveja() throws Exception {
        // given
        final DecrementarEstoqueCervejaInputData inputData = DecrementarEstoqueCervejaInputData.builder()
                .quantidade(10)
                .build();

        final DecrementarEstoqueCervejaOutputData outputData = DecrementarEstoqueCervejaOutputData.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo("LAGER")
                .build();

        // when
        when(useCase.execute(any(DecrementarEstoqueCervejaInputData.class))).thenReturn(outputData);

        // then
        var responseBodyString = mockMvc.perform(patch(URL_PATH + "/1/decremento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseBody = GSON.fromJson(responseBodyString, DecrementarEstoqueCervejaOutputData.class);

        assertThat(outputData, is(equalTo(responseBody)));
    }

    @Test
    public void quandoRealizarPATCHComUmIdInvalidoEntaoDeveRetornarErro() throws Exception {
        // given
        final DecrementarEstoqueCervejaInputData inputData = DecrementarEstoqueCervejaInputData.builder()
                .quantidade(10)
                .build();

        // when
        when(useCase.execute(any(DecrementarEstoqueCervejaInputData.class))).thenThrow(CervejaNaoEncontradaException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL_PATH + "/1/decremento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void quandoRealizarPATCHEQuantidadeEEstoqueFicarNegativoEntaoDeveRetornarErro() throws Exception {
        // given
        final DecrementarEstoqueCervejaInputData inputData = DecrementarEstoqueCervejaInputData.builder()
                .quantidade(10)
                .build();

        // when
        when(useCase.execute(any(DecrementarEstoqueCervejaInputData.class))).thenThrow(EstoqueNegativoException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.patch(URL_PATH + "/1/decremento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isInternalServerError());
    }
}
