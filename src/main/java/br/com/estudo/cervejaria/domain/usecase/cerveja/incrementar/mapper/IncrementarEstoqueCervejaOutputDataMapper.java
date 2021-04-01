package br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.mapper;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.incrementar.IncrementarEstoqueCervejaOutputData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IncrementarEstoqueCervejaOutputDataMapper {
    IncrementarEstoqueCervejaOutputDataMapper INSTANCE = Mappers.getMapper(IncrementarEstoqueCervejaOutputDataMapper.class);

    IncrementarEstoqueCervejaOutputData toOutputData(Cerveja cerveja);
}
