package br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar;

import br.com.estudo.cervejaria.domain.data.CervejaDataProvider;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.exception.CervejaNaoEncontradaException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.exception.EstoqueNegativoException;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.mapper.DecrementarEstoqueCervejaOutputDataMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DecrementarEstoqueCervejaUseCaseImpl implements DecrementarEstoqueCervejaUseCase {

    private final CervejaDataProvider cervejaDataProvider;

    private final DecrementarEstoqueCervejaOutputDataMapper outputDataMapper;

    @Override
    public DecrementarEstoqueCervejaOutputData execute(DecrementarEstoqueCervejaInputData inputData) {
        final var cerveja = buscarCerveja(inputData.getId());
        final var quantidadeAtualizada = calcularQuantidadeEstoqueAtualizado(cerveja, inputData);
        validarEstoquePositivoOuVazio(quantidadeAtualizada);

        cerveja.setQuantidade(quantidadeAtualizada);
        return outputDataMapper.toOutputData(cervejaDataProvider.salvar(cerveja));
    }

    private Cerveja buscarCerveja(long id) {
        return cervejaDataProvider.buscarPorId(id).orElseThrow(CervejaNaoEncontradaException::new);
    }

    private int calcularQuantidadeEstoqueAtualizado(Cerveja cerveja, DecrementarEstoqueCervejaInputData inputData) {
        return cerveja.getQuantidade() - inputData.getQuantidade();
    }

    private void validarEstoquePositivoOuVazio(int quantidadeAtualizada) {
        if (quantidadeAtualizada < 0) {
            throw new EstoqueNegativoException();
        }
    }
}
