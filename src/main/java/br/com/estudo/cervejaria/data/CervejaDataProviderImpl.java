package br.com.estudo.cervejaria.data;

import br.com.estudo.cervejaria.data.repository.CervejaRepository;
import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CervejaDataProviderImpl implements CervejaDataProvider {

    private final CervejaRepository repository;

    @Override
    public Cerveja salvar(Cerveja cerveja) {
        return null;
    }

    @Override
    public Optional<Cerveja> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }
}
