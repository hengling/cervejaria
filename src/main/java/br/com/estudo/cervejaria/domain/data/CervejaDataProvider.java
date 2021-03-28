package br.com.estudo.cervejaria.domain.data;

import br.com.estudo.cervejaria.domain.entity.Cerveja;

import java.util.List;
import java.util.Optional;

public interface CervejaDataProvider {
    Cerveja salvar(Cerveja cerveja);

    Optional<Cerveja> buscarPorId(long id);

    Optional<Cerveja> buscarPorNome(String nome);

    List<Cerveja> buscarTodos();

    void remover(Cerveja cerveja);
}
