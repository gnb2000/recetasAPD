package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="itemIngredientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemIngrediente {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idItemIngrediente;

    @ManyToOne()
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    private Integer cantidad;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}
