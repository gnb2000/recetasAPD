package com.recetasAPD.recetasAPD.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class NotValidNicknameException extends RuntimeException{

    @Getter
    private List<String> aliasRecomendados;

    public NotValidNicknameException(String message, List<String> aliasRecomendados){
        super(message);
        this.aliasRecomendados = aliasRecomendados;
    }
}
