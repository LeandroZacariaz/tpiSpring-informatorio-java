package com.info.app.projectapptpi.dto.receta;

import java.util.List;
import java.util.UUID;

import com.info.app.projectapptpi.domain.enums.DificultadRecetaEnum;
import com.info.app.projectapptpi.dto.paso.PasoDto;

public record RecetaDto(
    UUID id_receta,
    String nombre,
    DificultadRecetaEnum dificultad,
    String descripcion,
    UUID id_categoria,
    List<PasoDto> pasos) {

}
