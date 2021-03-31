package br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.mapper;

import br.com.estudo.cervejaria.domain.entity.Cerveja;
import br.com.estudo.cervejaria.domain.usecase.cerveja.buscarlista.BuscarListaCervejaOutputData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BuscarListaCervejaOutputDataMapper {

    BuscarListaCervejaOutputDataMapper INSTANCE = Mappers.getMapper(BuscarListaCervejaOutputDataMapper.class);

    default BuscarListaCervejaOutputData toOutputData(List<Cerveja> cervejaList) {
        return BuscarListaCervejaOutputData.builder()
                .itens(cervejaList.stream()
                        .map(cerveja -> BuscarListaCervejaOutputData.Item.builder()
                                .id(cerveja.getId())
                                .nome(cerveja.getNome())
                                .marca(cerveja.getMarca())
                                .max(cerveja.getMax())
                                .quantidade(cerveja.getQuantidade())
                                .tipo(cerveja.getTipo().toString())
                                .build()
                        )
                        .collect(Collectors.toList()))
                .build();
    }

}
