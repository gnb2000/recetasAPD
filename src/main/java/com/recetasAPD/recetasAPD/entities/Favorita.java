package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Favoritas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorita {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFavorita;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}
