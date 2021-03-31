package br.com.estudo.cervejaria.entrypoint.cerveja;

import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaOutputData;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaUseCase;
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
    public BuscarListaCervejaOutputData buscarListaCerveja() {
        return useCase.execute();
    }
}
