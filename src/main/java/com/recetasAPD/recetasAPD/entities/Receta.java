package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToOne()
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private String nombre;
    private Integer porciones;

    @ManyToOne
    @JoinColumn(name = "idTipo")
    private Tipo tipo;

    private Integer cantidadPersonas;
    private Integer estado; // 0 = Pendiente, 1 = Rechazada , 2 = Aceptada,3 = Favorita (Personalizada)
    @Column(length = 1000)
    private String descripcion;

    @OneToMany(mappedBy= "receta",cascade = CascadeType.ALL)
    private List<Foto> foto;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL)
    private List<ItemIngrediente> ingredientes;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL)
    private List<Paso> pasos;

    @OneToOne(mappedBy = "receta",cascade = CascadeType.ALL)
    private RecetaExt recetaExt;


}


