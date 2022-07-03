package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="fotos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFoto;
    private String urlFoto;
    private String extension;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;

}
