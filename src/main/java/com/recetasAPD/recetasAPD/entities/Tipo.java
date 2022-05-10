package com.recetasAPD.recetasAPD.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipos")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tipo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;
    private String descripcion;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;
}
