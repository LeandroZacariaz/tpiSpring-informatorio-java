package com.info.app.projectapptpi.service.receta;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.info.app.projectapptpi.domain.Categoria;
import com.info.app.projectapptpi.domain.Paso;
import com.info.app.projectapptpi.domain.Receta;
import com.info.app.projectapptpi.dto.receta.RecetaCreateDto;
import com.info.app.projectapptpi.dto.receta.RecetaDetalleDto;
import com.info.app.projectapptpi.dto.receta.RecetaDto;
import com.info.app.projectapptpi.exceptions.ResourceNotFoundException;
import com.info.app.projectapptpi.mappers.receta.RecetaMapper;
import com.info.app.projectapptpi.repository.RecetaRepository;
import com.info.app.projectapptpi.service.categoria.CategoriaService;
import com.info.app.projectapptpi.service.paso.PasoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RecetaServiceImpl implements RecetaService{

    private RecetaMapper recetaMapper;
    private RecetaRepository recetaRepository;
    private CategoriaService categoriaService;
    private PasoService pasoService;

    @Override
    public RecetaDto createReceta(RecetaCreateDto recetaCreateDto){
        Receta recetaCreated = recetaMapper.recetaCreateDtoToReceta(recetaCreateDto);

        Categoria categoria = categoriaService.getCategoria(recetaCreateDto.id_categoria());

        recetaCreated.setCategoria(categoria);

        List<Paso> pasos=pasoService.crearPasos((recetaCreateDto.pasos()), recetaCreated);
        recetaCreated.setPasos(pasos);

        return recetaMapper.recetaToRecetaDto(recetaRepository.save(recetaCreated));
    }

    @Override
    public List<RecetaDto> getAllRecetas() {
        return recetaRepository.findAll().stream()
                        .map(receta -> recetaMapper.recetaToRecetaDto(receta))
                        .toList();
    }

    @Override
    public Optional<RecetaDetalleDto> getRecetaById(UUID idReceta){
        
        Receta receta = recetaRepository.findById(idReceta)
                    .orElseThrow(() -> new ResourceNotFoundException("La receta con id: " + idReceta + " no existe."));
        
        return Optional.of(recetaMapper.recetaToRecetaDetalleDto(receta));
    }
    
    @Override
    public void deleteReceta(UUID idReceta) {
        if ( recetaRepository.existsById(idReceta) ){
            recetaRepository.deleteById(idReceta);
        }else{
            throw new ResourceNotFoundException("La receta con id: "+idReceta+ " no existe.");
        }
    }

}
