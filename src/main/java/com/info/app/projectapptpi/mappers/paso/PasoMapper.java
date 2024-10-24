package com.info.app.projectapptpi.mappers.paso;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.app.projectapptpi.domain.Paso;
import com.info.app.projectapptpi.dto.paso.PasoDto;
import com.info.app.projectapptpi.mappers.ingrediente.IngredienteMapper;

@Mapper(componentModel = "spring", uses = IngredienteMapper.class)
public interface PasoMapper {
    @Mapping(target = "id_paso", ignore = true)
    @Mapping(target = "ingredientes", ignore = true)
    @Mapping(target = "receta", ignore = true)
    Paso pasoDtoToPaso(PasoDto pasoDto);

    PasoDto pasoToPasoDto(Paso paso);
}
