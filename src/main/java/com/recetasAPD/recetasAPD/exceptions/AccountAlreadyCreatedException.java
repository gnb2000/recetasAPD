package com.recetasAPD.recetasAPD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAlreadyCreatedException extends RuntimeException {

    public AccountAlreadyCreatedException(String message){
        super(message);
    }
}
