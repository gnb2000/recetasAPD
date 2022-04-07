package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Recetas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;
    private Integer idUsuario;
    private String titulo;
    private Integer porciones;
    private Integer idTipo;
    private Integer cantidadPersonas;
    @Column(length = 1000)
    private String descripcion;

    @OneToMany(mappedBy= "receta")
    private List<Foto> galeria;

    @OneToMany(mappedBy = "receta")
    private List<ItemIngrediente> Ingredientes;

    @OneToMany(mappedBy = "receta")
    private List<Paso> pasos;

}

