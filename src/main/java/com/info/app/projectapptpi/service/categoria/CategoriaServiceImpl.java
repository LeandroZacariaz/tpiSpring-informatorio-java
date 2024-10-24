package com.info.app.projectapptpi.service.categoria;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.info.app.projectapptpi.domain.Categoria;
import com.info.app.projectapptpi.domain.Receta;
import com.info.app.projectapptpi.dto.categoria.CategoriaCreateDto;
import com.info.app.projectapptpi.dto.categoria.CategoriaDto;
import com.info.app.projectapptpi.dto.receta.RecetaEnCategoriaDto;
import com.info.app.projectapptpi.exceptions.ResourceNotFoundException;
import com.info.app.projectapptpi.mappers.categoria.CategoriaMapper;
import com.info.app.projectapptpi.mappers.receta.RecetaMapper;
import com.info.app.projectapptpi.repository.CategoriaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private CategoriaMapper categoriaMapper;
    private CategoriaRepository categoriaRepository;
    private RecetaMapper recetaMapper;

    @Override
    public CategoriaDto createCategoria(CategoriaCreateDto categoriaCreateDto) {
        Categoria categoriaCreated= categoriaMapper.categoriaCreateDtoToCategoria(categoriaCreateDto);
        return categoriaMapper.categoriaToCategoriaDto(categoriaRepository.save(categoriaCreated));
    }

    @Override
    public Categoria getCategoria(UUID id_categoria){
        return categoriaRepository.findById(id_categoria)
                            .orElseThrow(() -> new ResourceNotFoundException("La categoria con id: "+ id_categoria + " no existe."));
    }
    
    @Override
    public List<CategoriaDto> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                        .map(categoria -> categoriaMapper.categoriaToCategoriaDto(categoria))
                        .toList();
    }

    @Override 
    public List<RecetaEnCategoriaDto> getRecetasByCategoriaId(UUID id_categoria){
        Categoria categoria=getCategoria(id_categoria);

        List<Receta> recetas = categoria.getRecetas();
        List<RecetaEnCategoriaDto> recetasEnCategoria = recetas.stream()
                                        .map(receta ->{
                                            RecetaEnCategoriaDto recetaEnCategoriaDto = recetaMapper.recetaToRecetaEnCategoriaDto(receta);
                                            Integer tiempoTotalPreparado = receta.getPasos().stream()
                                                                            .filter(paso -> !paso.getOpcional())
                                                                            .mapToInt(paso -> paso.getTiempo_estimado()).sum();
                                            return new RecetaEnCategoriaDto(
                                                recetaEnCategoriaDto.id_receta(),
                                                recetaEnCategoriaDto.nombre(),
                                                recetaEnCategoriaDto.dificultad(),
                                                recetaEnCategoriaDto.descripcion(),
                                                tiempoTotalPreparado);
                                        }).toList();
            
        return recetasEnCategoria;
            
        
    }
}
