package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.mapper;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarpornome.BuscarCervejaPorNomeOutputData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BuscarCervejaPorNomeOutputDataMapper {
    BuscarCervejaPorNomeOutputDataMapper INSTANCE = Mappers.getMapper(BuscarCervejaPorNomeOutputDataMapper.class);

    BuscarCervejaPorNomeOutputData toOutputData(Cerveja cerveja);
}
