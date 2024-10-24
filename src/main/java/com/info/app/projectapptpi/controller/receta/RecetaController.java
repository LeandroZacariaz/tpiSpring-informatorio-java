package com.info.app.projectapptpi.controller.receta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.app.projectapptpi.dto.ingrediente.IngredienteDetalleDto;
import com.info.app.projectapptpi.dto.receta.RecetaCreateDto;
import com.info.app.projectapptpi.dto.receta.RecetaDetalleDto;
import com.info.app.projectapptpi.dto.receta.RecetaDto;
import com.info.app.projectapptpi.service.ingrediente.IngredienteService;
import com.info.app.projectapptpi.service.receta.RecetaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/receta")
@AllArgsConstructor
public class RecetaController {
    
    private RecetaService recetaService;
    private IngredienteService ingredienteService;

    @PostMapping()
    public ResponseEntity<?> createReceta(@Valid @RequestBody RecetaCreateDto RecetaCreateDto){
        RecetaDto recetaDto = recetaService.createReceta(RecetaCreateDto);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(recetaDto);
    }

    @GetMapping()
    public List<RecetaDto> getAllRecetas(){
        
        return recetaService.getAllRecetas();

    }

    @GetMapping("/{idReceta}")
    public ResponseEntity<?> getRecetaById(@Valid @PathVariable("idReceta") UUID id_receta){
        Optional<RecetaDetalleDto> recetaOptional = recetaService.getRecetaById(id_receta);
        
        if (recetaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(recetaOptional.get());
        }else{
            return null;
        }
    }

    @DeleteMapping("/{idReceta}")
    public ResponseEntity<?> deleteReceta(@Valid @PathVariable UUID idReceta){

        recetaService.deleteReceta( idReceta );
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                            .build();
        

    }

    @GetMapping("/{idReceta}/ingrediente")
    public ResponseEntity<?> getIngredientesByReceta(@Valid @PathVariable("idReceta") UUID id_receta,
                                                @RequestParam(required = false) UUID id_paso){
        
        List<IngredienteDetalleDto> ingredientes;                                            

        if (id_paso==null) {
            //se devuelven todos los ingredientes de la receta
            ingredientes = ingredienteService.getIngredientesByRecetaId(id_receta);
        } else{
            //se devuelven solamente los ingredientes del paso
            ingredientes = ingredienteService.getIngredientesByPasoId(id_receta, id_paso);
        }
        
        return ResponseEntity.ok(ingredientes);
    }    
}
