package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.UnidadNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnidadHandlerException {


    @ExceptionHandler(UnidadNotFoundException.class)
    public ResponseEntity<String> handleUnidadNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
