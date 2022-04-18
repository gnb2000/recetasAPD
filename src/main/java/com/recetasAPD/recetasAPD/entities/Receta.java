package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Recetas")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Receta {



    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String titulo;
    private Integer porciones;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    private Integer cantidadPersonas;
    private Integer estado; // 0 = Pendiente, 1 = Rechazada , 2 = Aceptada
    @Column(length = 1000)
    private String descripcion;
    private LocalDateTime fecha;

    @OneToMany(mappedBy= "receta",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Foto> galeria;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemIngrediente> ingredientes;

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paso> pasos;


}


