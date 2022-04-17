package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.TipoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class TipoHandlerException {

    @ExceptionHandler(TipoNotFoundException.class)
    public ResponseEntity<String> handleTipoNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
