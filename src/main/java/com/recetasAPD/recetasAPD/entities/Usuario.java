package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String contraseña; //Para evitar problemas con la "ñ", despues habria que cambiar esto por "password"
    private boolean habilitado;
    private String nombre;
    private String avatar; // ver esto
    private Integer tipoUsuario;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorita> favoritos;

    public Usuario(String mail, String nickname){
        this.mail = mail;
        this.nickname = nickname;
    }



}