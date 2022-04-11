package com.recetasAPD.recetasAPD.handler;

import com.recetasAPD.recetasAPD.exceptions.EmailNotSendException;
import com.recetasAPD.recetasAPD.exceptions.UsuarioHandlerExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class EmailHandlerException {

    @ExceptionHandler(EmailNotSendException.class)
    public ResponseEntity<Object> handleEmailNotSendException(Exception e, WebRequest request){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
