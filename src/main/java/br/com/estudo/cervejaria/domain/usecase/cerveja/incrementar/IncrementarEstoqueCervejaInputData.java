package br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncrementarEstoqueCervejaInputData {
    private long id;

    @NotNull
    private int quantidade;
}
