package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tipos")
@Data //LOMBOOK: Te genera los Getters y Setters automaticos de los atributos de la clase
@AllArgsConstructor //LOMBOOK: Genera un constructor con todos los atributos de la clase
@NoArgsConstructor //LOMBOOK: Genera el constructor vacio
public class Tipo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    private String descripcion;
}
