package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.RemoverCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.remover.RemoverCervejaUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/cervejas")
@RequiredArgsConstructor
public class RemoverCervejaController {

    private final RemoverCervejaUseCase useCase;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove uma cerveja pelo id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Cerveja removida com sucesso"),
            @ApiResponse(code = 404, message = "Cerveja não encontrada pelo id informado")
    })
    public void removerCerveja(@PathVariable @NotNull Long id) {
        useCase.execute(new RemoverCervejaInputData(id));
    }
}
