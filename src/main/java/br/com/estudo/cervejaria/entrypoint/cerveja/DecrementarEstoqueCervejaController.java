package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaUseCase;
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
    public DecrementarEstoqueCervejaOutputData inserirCerveja(@PathVariable Long id, @RequestBody @Valid DecrementarEstoqueCervejaInputData inputData) {
        inputData.setId(id);
        return useCase.execute(inputData);
    }
}
