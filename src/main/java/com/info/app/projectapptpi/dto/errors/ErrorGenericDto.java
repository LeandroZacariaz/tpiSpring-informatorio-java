package com.info.app.projectapptpi.dto.errors;

import java.util.List;
import java.util.Map;

public record ErrorGenericDto(String mensaje, List<Map<String, String>> errores) {

}
