package br.com.estudo.cervejaria.domain.usecase.cerveja.inserir;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InserirCervejaInputData {

    @NotNull
    @Size(min = 1, max = 200)
    private String nome;

    @NotNull
    @Size(min = 1, max = 200)
    private String marca;

    @NotNull
    @Max(500)
    private int max;

    @NotNull
    @Max(100)
    private int quantidade;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Cerveja.Tipo tipo;
}
