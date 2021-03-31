package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaUseCase;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InserirCervejaControllerTest {

    private static final String URL_PATH = "/api/v1/cervejas";
    private static final Gson GSON = new Gson();

    private MockMvc mockMvc;

    @Mock
    private InserirCervejaUseCase useCase;

    @InjectMocks
    private InserirCervejaController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void quandoRealizarPOSTEntaoDeveCriarUmaCerveja() throws Exception {
        // given
        final InserirCervejaInputData inputData = InserirCervejaInputData.builder()
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo(Cerveja.Tipo.LAGER)
                .build();

        final InserirCervejaOutputData outputData = InserirCervejaOutputData.builder()
                .id(1L)
                .nome("Brahma")
                .marca("Ambev")
                .max(50)
                .quantidade(10)
                .tipo("LAGER")
                .build();

        // when
        when(useCase.execute(inputData)).thenReturn(outputData);

        // then
        var responseBodyString = mockMvc.perform(post(URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var responseBody = GSON.fromJson(responseBodyString, InserirCervejaOutputData.class);

        assertThat(outputData, is(equalTo(responseBody)));
    }

    @Test
    public void quandoRealizarPOSTSemAtributoRequeridoInformadoEntaoDeveRetornarErro() throws Exception {
        // given
        final InserirCervejaInputData inputData = InserirCervejaInputData.builder()
                .build();

        // then
        mockMvc.perform(post(URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(inputData)))
                .andExpect(status().isBadRequest());
    }

}
