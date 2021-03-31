package br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.mapper;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.inserir.InserirCervejaOutputData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InserirCervejaOutputDataMapper {

    InserirCervejaOutputDataMapper INSTANCE = Mappers.getMapper(InserirCervejaOutputDataMapper.class);

    InserirCervejaOutputData toOutputData(Cerveja cerveja);

}
