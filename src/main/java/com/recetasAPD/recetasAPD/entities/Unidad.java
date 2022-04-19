package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "unidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Unidad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUnidad;
    private String descripcion;

    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<ItemIngrediente> itemsIngrediente;
}
