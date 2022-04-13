package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.EmailNotSendException;
import com.recetasAPD.recetasAPD.exceptions.RecetasEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RecetasHandlerException {

    @ExceptionHandler(RecetasEmptyException.class)
    public ResponseEntity<String> handleRecetasEmptyException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
