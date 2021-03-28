package br.com.estudo.cervejaria.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cerveja {
    private Long id;
    private String nome;
    private String marca;
    private int max;
    private int quantidade;
    private Tipo tipo;

    @Getter
    @AllArgsConstructor
    public enum Tipo {
        LAGER("Lager"),
        MALZBIER("Malzbier"),
        WITBIER("Witbier"),
        WEISS("Weiss"),
        ALE("Ale"),
        IPA("IPA"),
        STOUT("Stout");

        private final String descricao;
    }
}
