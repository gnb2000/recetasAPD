package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "calificaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calificacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCalificacion;
    private Integer idUsuario;
    private Integer idReceta;
    private Integer calificacion;
    private String Comentario; // Preguntar
}
