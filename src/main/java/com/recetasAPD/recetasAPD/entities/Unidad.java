package com.recetasAPD.recetasAPD.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unidades")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Unidad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnidad;
    private String descripcion;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<ItemIngrediente> itemsIngrediente;
}
