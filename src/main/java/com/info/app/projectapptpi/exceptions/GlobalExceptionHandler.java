package com.info.app.projectapptpi.exceptions;

import java.util.Map;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.info.app.projectapptpi.dto.errors.ErrorDtoNotFound;
import com.info.app.projectapptpi.dto.errors.ErrorGenericDto;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorGenericDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        var errorList = ex.getFieldErrors().stream()
                .map( campoError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(campoError.getField(), campoError.getDefaultMessage());
                    return errorMap;
                        }
                ).toList();


            ErrorGenericDto erroresDto = new ErrorGenericDto(
                "Uno o mas campos tienen datos invalidos.",
                errorList);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroresDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDtoNotFound> handleNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDtoNotFound errorDtoNotFound=new ErrorDtoNotFound(webRequest.getDescription(false)
                                                                , ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDtoNotFound);
    }
}
