package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.exception.CervejaNaoEncontradaException;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BuscarCervejaPorNomeControllerTest {

    private static final String URL_PATH = "/api/v1/cervejas";
    private static final Gson GSON = new Gson();

    private MockMvc mockMvc;

    @Mock
    private BuscarCervejaPorNomeUseCase useCase;

    @InjectMocks
    private BuscarCervejaPorNomeController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    public void quandoRealizarGETComUmNomeValidoEntaoDeveRetornarCerveja() throws Exception {
        // given
        var outputData = BuscarCervejaPorNomeOutputData.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo("LAGER")
                .build();

        // when
        when(useCase.execute(any(BuscarCervejaPorNomeInputData.class))).thenReturn(outputData);

        // then
        var responseBodyString = mockMvc.perform(MockMvcRequestBuilders.get(URL_PATH + "/" + outputData.getNome())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseBody = GSON.fromJson(responseBodyString, BuscarCervejaPorNomeOutputData.class);

        assertThat(outputData, is(equalTo(responseBody)));
    }

    @Test
    public void quandoRealizarGETComUmNomeInvalidoEntaoDeveRetornarErro() throws Exception {
        // given
        var nomeInvalido = "invalido";

        // when
        when(useCase.execute(any(BuscarCervejaPorNomeInputData.class))).thenThrow(CervejaNaoEncontradaException.class);

        // then
        var responseBodyString = mockMvc.perform(MockMvcRequestBuilders.get(URL_PATH + "/" + nomeInvalido)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
