package com.recetasAPD.recetasAPD.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class UsuarioHandlerExceptionResponse {

    private String message;
    private HttpStatus status;
}
