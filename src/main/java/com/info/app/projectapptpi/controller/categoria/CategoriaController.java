package com.info.app.projectapptpi.controller.categoria;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.app.projectapptpi.dto.categoria.CategoriaCreateDto;
import com.info.app.projectapptpi.dto.categoria.CategoriaDto;
import com.info.app.projectapptpi.dto.receta.RecetaEnCategoriaDto;
import com.info.app.projectapptpi.service.categoria.CategoriaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/categoria")
@AllArgsConstructor
public class CategoriaController {
    
    private CategoriaService categoriaService;

    @PostMapping()
    public ResponseEntity<?> createCategoria(@Valid @RequestBody CategoriaCreateDto categoriaCreateDto){
        CategoriaDto categoriaDto=categoriaService.createCategoria(categoriaCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaDto);
    }

    @GetMapping()
    public List<CategoriaDto> getAllCategorias(){
        
        return categoriaService.getAllCategorias();

    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<?> getRecetasByCategoriaId(@PathVariable("idCategoria") UUID id_categoria){
        List<RecetaEnCategoriaDto> recetas = categoriaService.getRecetasByCategoriaId(id_categoria);

        return ResponseEntity.status(HttpStatus.OK).body(recetas);
    }
    
}
