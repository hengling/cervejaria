package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cervejas")
@RequiredArgsConstructor
public class DecrementarEstoqueCervejaController {

    private final DecrementarEstoqueCervejaUseCase useCase;

    @PatchMapping("/{id}/decremento")
    @ApiOperation(value = "Decrementa a quantidade do estoque de uma cerveja pelo id informado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estoque decrementado com sucesso"),
            @ApiResponse(code = 404, message = "Cerveja não encontrada pelo id informado")
    })
    public DecrementarEstoqueCervejaOutputData inserirCerveja(@PathVariable Long id, @RequestBody @Valid DecrementarEstoqueCervejaInputData inputData) {
        inputData.setId(id);
        return useCase.execute(inputData);
    }
}
