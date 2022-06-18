package com.recetasAPD.recetasAPD.dtos;

import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class CalificacionResponse {
    private Integer idCalificacion;
    private Usuario usuario;
    private Receta receta;
    private Float calificacion;
    private String comentarios;


}
