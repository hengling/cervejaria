package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.RemoverCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.RemoverCervejaUseCase;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.entrypoint.GlobalErrorHandler;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RemoverCervejaControllerTest {

    private static final String URL_PATH = "/api/v1/cervejas";

    private MockMvc mockMvc;

    @Mock
    private RemoverCervejaUseCase useCase;

    @InjectMocks
    private RemoverCervejaController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new GlobalErrorHandler())
                .build();
    }

    @Test
    public void quandoRealizarDELETEComIdValidoEntaoDeveRemoverCerveja() throws Exception {
        // given
        var id = 1L;

        // when
        doNothing().when(useCase).execute(new RemoverCervejaInputData(id));

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(URL_PATH + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void quandoRealizarDELETEComIdInvalidoEntaoDeveRetornarErro() throws Exception {
        // given
        var id = 1L;

        // when
        doThrow(CervejaNaoEncontradaException.class).when(useCase).execute(new RemoverCervejaInputData(id));

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(URL_PATH + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
