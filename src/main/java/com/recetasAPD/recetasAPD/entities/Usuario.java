package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String mail;
    private String nickname;
    private boolean habilitado;
    private String nombre;
    private String avatar; // ver esto
    private Integer tipoUsuario;



}
