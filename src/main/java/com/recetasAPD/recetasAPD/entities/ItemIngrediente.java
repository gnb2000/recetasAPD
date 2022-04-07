package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Pasos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemIngrediente {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idItemIngrediente;
    private Integer idIngrediente;
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}
