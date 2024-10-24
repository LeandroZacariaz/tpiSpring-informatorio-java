package com.info.app.projectapptpi.mappers.categoria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.info.app.projectapptpi.domain.Categoria;
import com.info.app.projectapptpi.dto.categoria.CategoriaCreateDto;
import com.info.app.projectapptpi.dto.categoria.CategoriaDto;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaCreateDto categoriaToCategoriaCreateDto(Categoria categoria);

    @Mapping(target = "id_categoria", ignore = true)
    @Mapping(target = "recetas", ignore = true)
    Categoria categoriaCreateDtoToCategoria(CategoriaCreateDto categoriaCreateDto);
    
    CategoriaDto categoriaToCategoriaDto(Categoria categoria);

    @Mapping(target = "recetas", ignore = true)
    Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto);
}
