package br.com.estudo.cervejaria.domain.usecase.cerveja.inserir;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.exception.CervejaJaCadastradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.mapper.InserirCervejaOutputDataMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InserirCervejaUseCaseImpl implements InserirCervejaUseCase {

    private final CervejaDataProvider cervejaDataProvider;

    private final InserirCervejaOutputDataMapper outputDataMapper;

    @Override
    public InserirCervejaOutputData execute(InserirCervejaInputData inputData) {
        validarSeCervejaJaExiste(inputData.getNome());
        final var novaCerveja = instanciarEntidade(inputData);
        final var cervejaSalva = salvarCerveja(novaCerveja);
        return outputDataMapper.toOutputData(cervejaSalva);
    }

    private void validarSeCervejaJaExiste(String nome) {
        cervejaDataProvider.buscarPorNome(nome).ifPresent(cerveja -> {
            throw new CervejaJaCadastradaException();
        });
    }

    private Cerveja instanciarEntidade(InserirCervejaInputData inputData) {
        return Cerveja.builder()
                .nome(inputData.getNome())
                .marca(inputData.getMarca())
                .max(inputData.getMax())
                .quantidade(inputData.getQuantidade())
                .tipo(inputData.getTipo())
                .build();
    }

    private Cerveja salvarCerveja(Cerveja cerveja) {
        return cervejaDataProvider.salvar(cerveja);
    }
}
