package com.info.app.projectapptpi.controller.paso;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.app.projectapptpi.dto.paso.PasoDto;
import com.info.app.projectapptpi.service.paso.PasoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/paso")
@AllArgsConstructor
public class PasoController {
    private PasoService pasoService;

    @GetMapping()
    public List<PasoDto> getAllPasos(){
        
        return pasoService.getAllPasos();

    }

    @PutMapping("/{idPaso}")
    public ResponseEntity<?> updatePaso(@Valid @PathVariable UUID idPaso, @Valid @RequestBody PasoDto pasoDto){
        PasoDto pasoUpdateDto=pasoService.actualizarPaso(idPaso, pasoDto);
        
        return ResponseEntity.ok(pasoUpdateDto);
    }
}
