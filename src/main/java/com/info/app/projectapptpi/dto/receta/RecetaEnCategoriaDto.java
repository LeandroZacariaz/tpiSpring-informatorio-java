package com.info.app.projectapptpi.dto.receta;

import java.util.UUID;

import com.info.app.projectapptpi.domain.enums.DificultadRecetaEnum;

public record RecetaEnCategoriaDto(UUID id_receta,
        String nombre,
        DificultadRecetaEnum dificultad,
        String descripcion,
        Integer tiempo_total_preparacion) {

}
