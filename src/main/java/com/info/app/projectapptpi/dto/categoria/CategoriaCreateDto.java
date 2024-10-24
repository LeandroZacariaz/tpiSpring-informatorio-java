package com.info.app.projectapptpi.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaCreateDto(
    @NotNull(message = "El nombre de la categoria no puede ser nulo.")
    @NotBlank(message = "El nombre de la categoria no puede estar vacio.")
    String nombre) {

}
