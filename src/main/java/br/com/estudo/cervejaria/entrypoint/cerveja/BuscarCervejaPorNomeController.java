package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeInputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/cervejas")
@RequiredArgsConstructor
public class BuscarCervejaPorNomeController {

    private final BuscarCervejaPorNomeUseCase useCase;

    @GetMapping("/{nome}")
    public BuscarCervejaPorNomeOutputData buscarCervejaPorNome(@PathVariable @NotNull @NotBlank String nome) {
        return useCase.execute(new BuscarCervejaPorNomeInputData(nome));
    }
}
