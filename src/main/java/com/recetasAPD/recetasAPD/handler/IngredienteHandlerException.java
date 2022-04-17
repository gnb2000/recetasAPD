package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.IngredienteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class IngredienteHandlerException {

    @ExceptionHandler(IngredienteNotFoundException.class)
    public ResponseEntity<String> handleIngredienteNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}