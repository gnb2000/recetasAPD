package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.dtos.RecetaResponse;
import com.recetasAPD.recetasAPD.exceptions.EmailNotSendException;
import com.recetasAPD.recetasAPD.exceptions.RecetaAlreadyCreatedException;
import com.recetasAPD.recetasAPD.exceptions.RecetaNotCreatedException;
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
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecetaNotCreatedException.class)
    public ResponseEntity<String> handleRecetaNotCreatedException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecetaAlreadyCreatedException.class)
    public ResponseEntity<RecetaResponse> handleRecetaAlreadyCreatedException(RecetaAlreadyCreatedException e){
        return new ResponseEntity<>(e.getReceta(), HttpStatus.CONFLICT);
    }
}
