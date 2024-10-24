package com.info.app.projectapptpi.service.receta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.info.app.projectapptpi.dto.receta.RecetaCreateDto;
import com.info.app.projectapptpi.dto.receta.RecetaDetalleDto;
import com.info.app.projectapptpi.dto.receta.RecetaDto;

public interface RecetaService {
    RecetaDto createReceta(RecetaCreateDto receta);
    List<RecetaDto> getAllRecetas();
    Optional<RecetaDetalleDto> getRecetaById(UUID id);
    void deleteReceta(UUID idReceta);
    
}
