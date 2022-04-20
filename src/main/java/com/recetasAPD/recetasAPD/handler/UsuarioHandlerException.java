package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.dtos.NotValidNicknameExceptionResponse;
import com.recetasAPD.recetasAPD.exceptions.*;
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

    @ExceptionHandler(NotValidNicknameException.class)
    public ResponseEntity<NotValidNicknameExceptionResponse> handleNotValidNicknameExceptions(NotValidNicknameException e){
        NotValidNicknameExceptionResponse response = new NotValidNicknameExceptionResponse(e.getMessage(),e.getAliasRecomendados());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidMailException.class)
    public ResponseEntity<String> handleNotValidMailExceptions(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountAlreadyCreatedException.class)
    public ResponseEntity<String> handleAccountAlreadyCreatedException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncompleteRegistrationException.class)
    public ResponseEntity<String> handleIncompleteRegistrationException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectCodeRecoveryException.class)
    public ResponseEntity<Boolean> handleIncorrectCodeRecoveryException(IncorrectCodeRecoveryException e){
        return new ResponseEntity<>(e.isSameCode(),HttpStatus.BAD_REQUEST);
    }




}
