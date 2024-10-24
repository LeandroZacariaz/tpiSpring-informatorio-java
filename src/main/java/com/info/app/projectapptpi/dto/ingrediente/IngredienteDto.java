package com.info.app.projectapptpi.dto.ingrediente;

import jakarta.validation.constraints.NotNull;

public record IngredienteDto(
    @NotNull(message = "El nombre del ingrediente no puede ser nulo.")
    String nombre,
    @NotNull(message = "La descripcion del ingrediente no puede estar vacia.")
    String descripcion
) {

}
