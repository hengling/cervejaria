package br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception.QuantidadeEmEstoqueExcedidaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.mapper.IncrementarEstoqueCervejaOutputDataMapper;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.exception.CervejaNaoEncontradaException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncrementarEstoqueCervejaUseCaseImpl implements IncrementarEstoqueCervejaUseCase {

    private final CervejaDataProvider cervejaDataProvider;

    private final IncrementarEstoqueCervejaOutputDataMapper outputDataMapper;

    @Override
    public IncrementarEstoqueCervejaOutputData execute(IncrementarEstoqueCervejaInputData inputData) {
        final var cerveja = buscarCerveja(inputData.getId());
        final var quantidadeAtualizada = calcularQuantidadeEstoqueAtualizado(cerveja, inputData);
        validarQuantidadeAtualizarDentroLimiteMaximo(cerveja, quantidadeAtualizada);

        cerveja.setQuantidade(quantidadeAtualizada);
        return outputDataMapper.toOutputData(cervejaDataProvider.salvar(cerveja));
    }

    private Cerveja buscarCerveja(long id) {
        return cervejaDataProvider.buscarPorId(id).orElseThrow(CervejaNaoEncontradaException::new);
    }

    private int calcularQuantidadeEstoqueAtualizado(Cerveja cerveja, IncrementarEstoqueCervejaInputData inputData) {
        return cerveja.getQuantidade() + inputData.getQuantidade();
    }

    private void validarQuantidadeAtualizarDentroLimiteMaximo(Cerveja cerveja, int quantidadeAtualizada) {
        if (quantidadeAtualizada > cerveja.getMax()) {
            throw new QuantidadeEmEstoqueExcedidaException();
        }
    }
}
