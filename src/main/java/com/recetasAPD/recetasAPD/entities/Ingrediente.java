package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Ingredientes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngrediente;
    private String nombre;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemIngrediente> itemsIngrediente;

    public Ingrediente(String nombre){
        this.nombre = nombre;
    }
}
