package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemIngredienteRequest {

    private Integer idIngrediente;
    private Integer cantidad;
    private Integer idUnidad;
    private String observaciones;
}
