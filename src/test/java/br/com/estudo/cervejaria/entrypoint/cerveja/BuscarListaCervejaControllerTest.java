package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaUseCase;
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

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BuscarListaCervejaControllerTest {

    private static final String URL_PATH = "/api/v1/cervejas";
    private static final Gson GSON = new Gson();

    private MockMvc mockMvc;

    @Mock
    private BuscarListaCervejaUseCase useCase;

    @InjectMocks
    private BuscarListaCervejaController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void quandoRealizarGETEExistirCervejasCadastradasEntaoDeveRetornarListaDeCervejas() throws Exception {
        // given
        var outputData = BuscarListaCervejaOutputData.builder()
                .itens(Collections.singletonList(
                        BuscarListaCervejaOutputData.Item.builder()
                                .id(1L)
                                .nome("Brahma")
                                .marca("Ambev")
                                .max(50)
                                .quantidade(10)
                                .tipo("LAGER")
                                .build())
                )
                .build();

        // when
        when(useCase.execute()).thenReturn(outputData);

        // then
        var responseBodyString = mockMvc.perform(MockMvcRequestBuilders.get(URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseBody = GSON.fromJson(responseBodyString, BuscarListaCervejaOutputData.class);

        assertThat(outputData, is(equalTo(responseBody)));
    }

    @Test
    public void quandoRealizarGETENaoExistirCervejasCadastradasEntaoDeveRetornarListaVazia() throws Exception {
        // given
        var outputData = BuscarListaCervejaOutputData.builder()
                .itens(Collections.emptyList())
                .build();

        // when
        when(useCase.execute()).thenReturn(outputData);

        // then
        var responseBodyString = mockMvc.perform(MockMvcRequestBuilders.get(URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseBody = GSON.fromJson(responseBodyString, BuscarListaCervejaOutputData.class);

        assertThat(outputData, is(equalTo(responseBody)));
    }
}
