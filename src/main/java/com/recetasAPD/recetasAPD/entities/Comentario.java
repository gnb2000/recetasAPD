package com.recetasAPD.recetasAPD.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comentario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;
}
