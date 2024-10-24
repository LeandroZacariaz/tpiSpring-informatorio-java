package com.info.app.projectapptpi.mappers.ingrediente;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.app.projectapptpi.domain.Ingrediente;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDetalleDto;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDto;

@Mapper(componentModel = "spring")
public interface IngredienteMapper {
    @Mapping(target = "id_ingrediente", ignore = true)
    Ingrediente ingredienteDtoToIngrediente(IngredienteDto ingredienteDto);

    IngredienteDetalleDto ingredienteToIngredienteDetalleDto(Ingrediente ingrediente);
}
