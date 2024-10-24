package com.info.app.projectapptpi.dto.receta;

import java.util.UUID;
import java.util.List;

import com.info.app.projectapptpi.domain.enums.DificultadRecetaEnum;
import com.info.app.projectapptpi.dto.categoria.CategoriaDto;
import com.info.app.projectapptpi.dto.paso.PasoDetalleDto;

public record RecetaDetalleDto(
            UUID id_receta,
            String nombre, 
            String descripcion,
            DificultadRecetaEnum dificultad,
            CategoriaDto categoria,
            List<PasoDetalleDto> pasos
) {

}
