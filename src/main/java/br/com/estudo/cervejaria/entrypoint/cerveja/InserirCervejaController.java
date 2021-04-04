package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cervejas")
@RequiredArgsConstructor
public class InserirCervejaController {

    private final InserirCervejaUseCase useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere uma nova cerveja")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cerveja inserida com sucesso"),
            @ApiResponse(code = 400, message = "Campos obrigatórios não informados ou com formato errado")
    })
    public InserirCervejaOutputData inserirCerveja(@RequestBody @Valid InserirCervejaInputData inputData) {
        return useCase.execute(inputData);
    }
}
