package com.recetasAPD.recetasAPD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailNotSendException extends RuntimeException{

    public EmailNotSendException(String message){
        super(message);
    }

}
