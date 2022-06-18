package com.recetasAPD.recetasAPD.dtos;


import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionRequest {
    private Integer idCalificacion;
    private Usuario usuario;
    private Receta receta;
    private Float calificacion;
    private String comentarios;


}
