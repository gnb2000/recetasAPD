package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Fotos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFoto;
    private String urlFoto;
    private String extension; //

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}