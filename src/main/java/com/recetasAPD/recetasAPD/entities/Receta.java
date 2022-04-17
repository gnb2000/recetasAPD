package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Recetas")
@Data
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipo tipo;

    private Integer cantidadPersonas;
    private Integer estado; // 0 = Pendiente, 1 = Rechazada , 2 = Aceptada
    @Column(length = 1000)
    private String descripcion;
    private LocalDateTime fecha;

    @OneToMany(mappedBy= "receta")
    private List<Foto> galeria;

    @OneToMany(mappedBy = "receta")
    private List<ItemIngrediente> ingredientes;

    @OneToMany(mappedBy = "receta")
    private List<Paso> pasos;


}


