package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.NotValidNicknameOrMailException;
import com.recetasAPD.recetasAPD.dtos.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
//Esta clase lo que hace es estar escuchando todas las excepciones que se tiren (por ej, cuando el service ponemos new Exception)
public class UsuarioHandlerException {

    //Este metodo solo va a entrar cuando se detecte que lanzo una excepcion del tipo "Exception"
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotValidNicknameOrMailException.class)
    public ResponseEntity<String> handleNotValidNicknameOrMailExceptions(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
