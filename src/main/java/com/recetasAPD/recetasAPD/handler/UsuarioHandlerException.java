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
    public ResponseEntity<Object> handleAllExceptions(Exception e){
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ExceptionHandler(NotValidNicknameOrMailException.class)
    public ResponseEntity<Object> handleNotValidNicknameOrMailExceptions(Exception e){
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
