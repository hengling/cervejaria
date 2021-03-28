package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarCervejaPorNomeInputData {
    private String nome;
}
