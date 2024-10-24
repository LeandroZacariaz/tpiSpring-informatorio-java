package com.info.app.projectapptpi.service.paso;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.info.app.projectapptpi.domain.Ingrediente;
import com.info.app.projectapptpi.domain.Paso;
import com.info.app.projectapptpi.domain.Receta;
import com.info.app.projectapptpi.dto.ingrediente.IngredienteDto;
import com.info.app.projectapptpi.dto.paso.PasoDto;
import com.info.app.projectapptpi.exceptions.ResourceNotFoundException;
import com.info.app.projectapptpi.mappers.paso.PasoMapper;
import com.info.app.projectapptpi.repository.PasoRepository;
import com.info.app.projectapptpi.service.ingrediente.IngredienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PasoServiceImpl implements PasoService {

    private PasoMapper pasoMapper;
    private IngredienteService ingredienteService;
    private PasoRepository pasoRepository;
    
    @Override
    public List<Paso> crearPasos(List<PasoDto> pasos, Receta receta) {
        return pasos.stream()
        .map(pasoDto -> {
            Paso paso = pasoMapper.pasoDtoToPaso(pasoDto);
            paso.setReceta(receta); //asigna la receta al paso

            List<Ingrediente> ingredientes = ingredienteService.crearIngredientes(pasoDto.ingredientes());

            paso.setIngredientes(ingredientes);
            return paso;
        })
        .toList();
    }

    @Override
    public List<PasoDto> getAllPasos() {
        return pasoRepository.findAll().stream()
                        .map(paso -> pasoMapper.pasoToPasoDto(paso))
                        .toList();
    }

    @Override
    public PasoDto actualizarPaso(UUID id_paso, PasoDto pasoDto){

        Paso paso = pasoRepository.findById(id_paso)
            .orElseThrow(() -> new ResourceNotFoundException("El paso con id: " + id_paso + " no existe."));

        paso.setDescripcion(pasoDto.descripcion());
        paso.setTiempo_estimado(pasoDto.tiempo_estimado());
        paso.setOpcional(pasoDto.opcional());

        List<Ingrediente> ingredientesActuales=paso.getIngredientes();
        List<IngredienteDto> ingredientesNuevos=pasoDto.ingredientes();

        List<Ingrediente> ingredientesActualizados=ingredienteService.actualizarCantidadIngredientes(ingredientesActuales, ingredientesNuevos);
        paso.setIngredientes(ingredientesActualizados);
        pasoRepository.save(paso);
        return pasoDto;
    }

    

}
