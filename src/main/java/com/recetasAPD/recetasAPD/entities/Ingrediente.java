package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Ingredientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngrediente;
    private String nombre;
}
