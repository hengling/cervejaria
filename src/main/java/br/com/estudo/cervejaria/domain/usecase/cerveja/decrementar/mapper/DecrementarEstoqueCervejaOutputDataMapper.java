package br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.mapper;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.decrementar.DecrementarEstoqueCervejaOutputData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DecrementarEstoqueCervejaOutputDataMapper {
    DecrementarEstoqueCervejaOutputDataMapper INSTANCE = Mappers.getMapper(DecrementarEstoqueCervejaOutputDataMapper.class);

    DecrementarEstoqueCervejaOutputData toOutputData(Cerveja cerveja);
}
