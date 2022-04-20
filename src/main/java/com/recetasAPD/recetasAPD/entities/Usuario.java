package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String mail;
    private String nickname;
    private String password; //Para evitar problemas con la "ñ", despues habria que cambiar esto por "password"
    private boolean habilitado;
    private String nombre;

    private String avatar; // Analizar si conviene guardar directamente el link o hacer una clase Avatar
    private Integer tipoUsuario;
    private String recoveryCode;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorita> favoritos;

    public Usuario(String mail, String nickname){
        this.mail = mail;
        this.nickname = nickname;
    }



}
