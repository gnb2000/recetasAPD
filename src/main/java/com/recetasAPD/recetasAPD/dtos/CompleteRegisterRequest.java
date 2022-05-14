package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteRegisterRequest {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String password;
}
