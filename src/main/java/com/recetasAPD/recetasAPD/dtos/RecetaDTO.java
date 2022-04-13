package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDTO {

    private Integer idReceta;
    private Integer idUsuario;
    private String titulo;
    private Integer porciones;
    private Integer idTipo;
    private Integer cantidadPersonas;
   // @Column(length = 1000)
    private String descripcion;
}
