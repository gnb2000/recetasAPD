package com.recetasAPD.recetasAPD.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RecetasEmptyException extends RuntimeException {
    public RecetasEmptyException(String message){super(message);}
}
