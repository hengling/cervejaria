package br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecrementarEstoqueCervejaOutputData {
    private long id;
    private String nome;
    private String marca;
    private int max;
    private int quantidade;
    private String tipo;
}
