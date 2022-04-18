package com.recetasAPD.recetasAPD.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipos")
@Getter
@Setter
@AllArgsConstructor //LOMBOOK: Genera un constructor con todos los atributos de la clase
@NoArgsConstructor //LOMBOOK: Genera el constructor vacio
public class Tipo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;
    private String descripcion;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;
}
