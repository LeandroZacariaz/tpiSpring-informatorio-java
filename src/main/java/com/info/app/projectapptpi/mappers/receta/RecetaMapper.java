package com.info.app.projectapptpi.mappers.receta;

import com.info.app.projectapptpi.dto.receta.RecetaCreateDto;
import com.info.app.projectapptpi.dto.receta.RecetaDetalleDto;
import com.info.app.projectapptpi.dto.receta.RecetaDto;
import com.info.app.projectapptpi.dto.receta.RecetaEnCategoriaDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.app.projectapptpi.domain.Receta;

@Mapper(componentModel = "spring")
public interface RecetaMapper {
    @Mapping(target = "id_receta", ignore = true)
    @Mapping(target = "pasos", ignore = true)
    @Mapping(source = "id_categoria", target = "categoria.id_categoria")
    Receta recetaCreateDtoToReceta(RecetaCreateDto recetaCreateDto);

    @Mapping(source = "categoria.id_categoria", target = "id_categoria")
    RecetaDto recetaToRecetaDto(Receta receta);

    RecetaDetalleDto recetaToRecetaDetalleDto (Receta receta);

    @Mapping(target = "tiempo_total_preparacion", ignore = true)
    RecetaEnCategoriaDto recetaToRecetaEnCategoriaDto (Receta receta);
}
