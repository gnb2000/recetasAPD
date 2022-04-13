package com.recetasAPD.recetasAPD.exceptions;

import lombok.Getter;

public class IncorrectCodeRecoveryException extends RuntimeException{

    @Getter
    private boolean sameCode;

    public IncorrectCodeRecoveryException(boolean sameCode){
        this.sameCode = sameCode;
    }
}
