package br.com.estudo.cervejaria.data;

import br.com.estudo.cervejaria.data.converter.CervejaMapper;
import br.com.estudo.cervejaria.data.repository.CervejaRepository;
import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CervejaDataProviderImpl implements CervejaDataProvider {

    private final CervejaRepository repository;

    private final CervejaMapper mapper;

    @Override
    public Cerveja salvar(Cerveja cerveja) {
        final var entidadeSalva = repository.save(mapper.fromBusinessEntity(cerveja));
        return mapper.toBusinessEntity(entidadeSalva);
    }

    @Override
    public Optional<Cerveja> buscarPorNome(String nome) {
        final var entidade = repository.findByNome(nome);
        return entidade.map(mapper::toBusinessEntity);
    }

    @Override
    public List<Cerveja> buscarTodos() {
        final var entidades = repository.findAll();
        return entidades.stream()
                .map(mapper::toBusinessEntity)
                .collect(Collectors.toList());
    }
}
