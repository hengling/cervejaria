package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscarListaCervejaOutputData {

    private List<BuscarListaCervejaOutputData.Item> itens;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private long id;
        private String nome;
        private String marca;
        private int max;
        private int quantidade;
        private String tipo;
    }
}
