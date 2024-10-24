package com.info.app.projectapptpi.dto.paso;

import java.util.UUID;

import com.info.app.projectapptpi.dto.ingrediente.IngredienteDto;

import java.util.List;


public record PasoDetalleDto(
        UUID id_paso,
        Integer tiempo_estimado,
        List<IngredienteDto> ingredientes
) {

}
