package com.info.app.projectapptpi.service.ingrediente;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.info.app.projectapptpi.domain.Ingrediente;
import com.info.app.projectapptpi.domain.Paso;
import com.info.app.projectapptpi.domain.Receta;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDetalleDto;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDto;
import com.info.app.projectapptpi.exceptions.ResourceNotFoundException;
import com.info.app.projectapptpi.mappers.ingrediente.IngredienteMapper;
import com.info.app.projectapptpi.repository.RecetaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class IngredienteServiceImpl implements IngredienteService {
    private IngredienteMapper ingredienteMapper;
    private RecetaRepository recetaRepository;
    
    @Override
    public List<Ingrediente> crearIngredientes(List<IngredienteDto> ingredientes) {
        return ingredientes.stream()
                            .map(ingrediente -> ingredienteMapper.ingredienteDtoToIngrediente(ingrediente))
                            .toList();
    }

    @Override
    public List<IngredienteDetalleDto> getIngredientesByRecetaId(UUID id_receta){
        Receta receta = recetaRepository.findById(id_receta)
                        .orElseThrow(() -> new ResourceNotFoundException("La receta con id: "+id_receta+" no existe."));
        
        return receta.getPasos().stream()
                            .flatMap(paso -> paso.getIngredientes().stream())
                            .map(ingrediente-> ingredienteMapper.ingredienteToIngredienteDetalleDto(ingrediente))
                            .collect(Collectors.toList());
    }

    @Override
    public List<IngredienteDetalleDto> getIngredientesByPasoId(UUID id_receta, UUID id_paso){

        Receta receta = recetaRepository.findById(id_receta)
                        .orElseThrow(() -> new ResourceNotFoundException("La receta con id: "+id_receta+" no existe."));
        
        Paso paso = receta.getPasos().stream()
                            .filter(p -> p.getId_paso().equals(id_paso))
                            .findFirst()
                            .orElseThrow(() -> new ResourceNotFoundException("El paso con id: "+id_paso+" no existe."));

        return paso.getIngredientes().stream()
                    .map(ingrediente -> ingredienteMapper.ingredienteToIngredienteDetalleDto(ingrediente))
                    .collect(Collectors.toList());
    }

    @Override
    public List<Ingrediente> actualizarCantidadIngredientes(List<Ingrediente> ingredientesActuales
                                                    , List<IngredienteDto> ingredientesNuevos){
        //elimino de la lista actual los ingredientes que no estan en la lista de ingredientes nuevos                                                
        ingredientesActuales.removeIf(ingredienteActual -> ingredientesNuevos.stream()
                                                        .noneMatch(ingredienteDto -> ingredienteDto.nombre()
                                                                    .equalsIgnoreCase(ingredienteActual.getNombre())));
        //agrego los ingredientes nuevos que no estan en la lista de ingredientes actuales                                                            
        for(IngredienteDto ingredienteDto: ingredientesNuevos){ 
            boolean exists = ingredientesActuales.stream()
                            .anyMatch(ingredienteActual -> ingredienteActual.getNombre()
                                                    .equalsIgnoreCase(ingredienteDto.nombre()));
            
            if (!exists) {
                ingredientesActuales.add(ingredienteMapper.ingredienteDtoToIngrediente(ingredienteDto));
            }
        }
        return ingredientesActuales;                                                
    }

}
