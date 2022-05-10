package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="utilizados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilizado {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idUtilizado;

    @ManyToOne
    @JoinColumn(name = "idIngrediente")
    private Ingrediente ingrediente;

    private float cantidad;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;

    @ManyToOne
    @JoinColumn(name = "idUnidad")
    private Unidad unidad;

    public Utilizado(Ingrediente ingrediente, float cantidad, String observaciones, Receta nuevaReceta, Unidad unidad) {
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
        this.observaciones = observaciones;
        this.receta = nuevaReceta;
        this.unidad = unidad;

    }
}
