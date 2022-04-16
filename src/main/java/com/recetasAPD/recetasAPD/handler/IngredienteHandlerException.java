package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.IngredienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IngredienteHandlerException {

    @ExceptionHandler(IngredienteNotFoundException.class)
    public ResponseEntity<String> handleIngredienteNotFoundException(Exception e){
        return new ResponseEntity<>("No se encontro un Ingrediente con el ID ingresado", HttpStatus.NOT_FOUND);
    }
}
