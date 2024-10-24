package com.info.app.projectapptpi.dto.receta;

import java.util.List;
import java.util.UUID;

import com.info.app.projectapptpi.domain.enums.DificultadRecetaEnum;
import com.info.app.projectapptpi.dto.paso.PasoDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecetaCreateDto(

                @NotNull(message = "El nombre de la receta no puede ser nulo.")
                @NotBlank(message = "El nombre de la receta no puede estar vacio.")
                String nombre, 
                DificultadRecetaEnum dificultad,
                @NotNull(message = "La descripcion de la receta no puede ser nula.")
                @NotBlank(message = "La descripcion de la receta no puede estar vacia.")
                @Size(max = 5000, message = "La descripcion de la receta no puede superar los 5000 caracteres.")
                String descripcion,
                UUID id_categoria,
                List<@Valid PasoDto> pasos) {

}
