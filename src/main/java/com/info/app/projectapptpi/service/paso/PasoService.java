package com.info.app.projectapptpi.service.paso;

import java.util.List;
import java.util.UUID;

import com.info.app.projectapptpi.domain.Paso;
import com.info.app.projectapptpi.domain.Receta;
import com.info.app.projectapptpi.dto.paso.PasoDto;;

public interface PasoService {
    List<Paso> crearPasos(List<PasoDto> pasos, Receta receta);
    PasoDto actualizarPaso(UUID id_paso, PasoDto pasoDto);
    List<PasoDto> getAllPasos();
}
