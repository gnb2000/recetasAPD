package com.recetasAPD.recetasAPD.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "calificaciones")
@Data
@Builder
@Getter
@Setter
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

    private Float calificacion;
    private String comentarios;



}
