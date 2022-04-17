package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Pasos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaso;
    private Integer nroPaso;
    @Column(length = 2000)
    private String descripcion;

    @OneToMany(mappedBy= "paso")
    private List<Multimedia> galeria;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}
