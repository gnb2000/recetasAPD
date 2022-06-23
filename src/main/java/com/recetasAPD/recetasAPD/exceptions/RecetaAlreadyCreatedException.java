package com.recetasAPD.recetasAPD.exceptions;

import com.recetasAPD.recetasAPD.dtos.RecetaResponse;

public class RecetaAlreadyCreatedException extends RuntimeException{

    private RecetaResponse receta;

    public RecetaAlreadyCreatedException(String message, RecetaResponse receta){
        super(message);
        this.receta = receta;
    }

    public RecetaResponse getReceta() {
        return receta;
    }

}
