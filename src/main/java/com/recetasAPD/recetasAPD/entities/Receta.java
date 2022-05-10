package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="recetas")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private String nombre;
    private Integer porciones;

    @ManyToOne
    @JoinColumn(name = "idTipo")
    private Tipo tipo;

    private Integer cantidadPersonas;
    @Column(length = 1000)
    private String descripcion;

    @OneToMany(mappedBy= "receta",cascade = CascadeType.ALL)
    private List<Foto> foto;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL)
    private List<Utilizado> ingredientes;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL)
    private List<Paso> pasos;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<Calificacion> calificaciones;

    @OneToOne(mappedBy = "receta",cascade = CascadeType.MERGE)
    private RecetaExt recetaExt;
}


