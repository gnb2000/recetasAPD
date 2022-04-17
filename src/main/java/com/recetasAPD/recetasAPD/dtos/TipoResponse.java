package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoResponse {

    private Integer idTipo;
    private String descripcion;

}
