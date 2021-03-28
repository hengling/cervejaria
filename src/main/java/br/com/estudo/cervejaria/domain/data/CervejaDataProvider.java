package br.com.estudo.cervejaria.domain.data;

import br.com.estudo.cervejaria.domain.entity.Cerveja;

import java.util.Optional;

public interface CervejaDataProvider {
    Cerveja salvar(Cerveja cerveja);

    Optional<Cerveja> buscarPorNome(String nome);
}
