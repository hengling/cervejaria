package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaUseCase;
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
public class IncrementarEstoqueCervejaController {

    private final IncrementarEstoqueCervejaUseCase useCase;

    @PatchMapping("/{id}/incremento")
    public IncrementarEstoqueCervejaOutputData inserirCerveja(@PathVariable Long id, @RequestBody @Valid IncrementarEstoqueCervejaInputData inputData) {
        inputData.setId(id);
        return useCase.execute(inputData);
    }
}
