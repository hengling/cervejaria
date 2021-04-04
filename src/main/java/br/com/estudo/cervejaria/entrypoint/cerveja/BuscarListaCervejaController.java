package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/cervejas")
@RequiredArgsConstructor
public class BuscarListaCervejaController {

    private final BuscarListaCervejaUseCase useCase;

    @GetMapping
    @ApiOperation(value = "Retorna a lista de cervejas registradas no sistema")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de todas as cervejas registradas no sistema"),
    })
    public BuscarListaCervejaOutputData buscarListaCerveja() {
        return useCase.execute();
    }
}
