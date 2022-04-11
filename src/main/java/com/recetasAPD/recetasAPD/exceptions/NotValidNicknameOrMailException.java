package com.recetasAPD.recetasAPD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidNicknameOrMailException extends RuntimeException{

    public NotValidNicknameOrMailException(String message){
        super(message);
    }
}
