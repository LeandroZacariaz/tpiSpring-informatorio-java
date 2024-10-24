package com.info.app.projectapptpi.dto.paso;

import java.util.List;

import com.info.app.projectapptpi.dto.ingrediente.IngredienteDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PasoDto(
    @NotNull(message = "La descripcion del paso no puede ser nula.")
    @NotBlank(message = "La descripcion del paso no puede estar vacia.")
    String descripcion,
    @NotNull(message = "El tiempo estimado del no puede ser nulo.")
    Integer tiempo_estimado,
    @NotNull(message = "El campo opcional del paso no puede ser nulo.")
    Boolean opcional,
    List<@Valid IngredienteDto> ingredientes
) {

}
