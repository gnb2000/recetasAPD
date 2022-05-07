package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilizadoRequest {

    private Integer idReceta;
    private Integer idIngrediente;
    private Integer cantidad;
    private Integer idUnidad;
    private String observaciones;
}
