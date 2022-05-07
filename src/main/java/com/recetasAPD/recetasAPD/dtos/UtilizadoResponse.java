package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilizadoResponse {

    private Integer idItemIngrediente;
    private IngredienteResponse ingrediente;
    private Integer cantidad;
    private UnidadResponse unidad;
    private String observaciones;
}
