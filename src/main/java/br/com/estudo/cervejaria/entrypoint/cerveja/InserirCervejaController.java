package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaUseCase;
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
    public InserirCervejaOutputData inserirCerveja(@RequestBody @Valid InserirCervejaInputData inputData) {
        return useCase.execute(inputData);
    }
}
