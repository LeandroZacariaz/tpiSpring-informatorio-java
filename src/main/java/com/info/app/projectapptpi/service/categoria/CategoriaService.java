package com.info.app.projectapptpi.service.categoria;

import java.util.List;
import java.util.UUID;

import com.info.app.projectapptpi.domain.Categoria;
import com.info.app.projectapptpi.dto.categoria.CategoriaCreateDto;
import com.info.app.projectapptpi.dto.categoria.CategoriaDto;
import com.info.app.projectapptpi.dto.receta.RecetaEnCategoriaDto;

public interface CategoriaService {
    CategoriaDto createCategoria(CategoriaCreateDto categoria);

    List<CategoriaDto> getAllCategorias();

    List<RecetaEnCategoriaDto> getRecetasByCategoriaId(UUID id_categoria);

    Categoria getCategoria(UUID id_categoria);
}
