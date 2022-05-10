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

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;

    private Integer calificacion;
    private String comentarios;
}
