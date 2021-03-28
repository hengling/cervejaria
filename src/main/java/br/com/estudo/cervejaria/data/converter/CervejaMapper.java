package br.com.estudo.cervejaria.data.converter;

import br.com.estudo.cervejaria.data.entity.CervejaEntity;
import br.com.estudo.cervejaria.domain.entity.Cerveja;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CervejaMapper {

    CervejaMapper INSTANCE = Mappers.getMapper(CervejaMapper.class);

    Cerveja toBusinessEntity(CervejaEntity beerDTO);

    CervejaEntity fromBusinessEntity(Cerveja beer);
}
