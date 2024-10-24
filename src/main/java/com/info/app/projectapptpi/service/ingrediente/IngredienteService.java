package com.info.app.projectapptpi.service.ingrediente;

import java.util.List;
import java.util.UUID;

import com.info.app.projectapptpi.domain.Ingrediente;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDetalleDto;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDto;

public interface IngredienteService {
    List<Ingrediente> crearIngredientes(List<IngredienteDto> ingredientes);

    List<IngredienteDetalleDto> getIngredientesByRecetaId(UUID id_receta);

    List<IngredienteDetalleDto> getIngredientesByPasoId(UUID id_receta, UUID id_paso);

    List<Ingrediente> actualizarCantidadIngredientes(List<Ingrediente> ingredientesActuales,List<IngredienteDto> ingredientesNuevos);

    

    
}
