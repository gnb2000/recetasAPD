package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String mail;
    private String nickname;
    private boolean habilitado;
    private String nombre;
    private String avatar; // LINK
    private Integer tipoUsuario;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> recetas;

    @OneToMany(mappedBy = "usuario",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorita> favoritos;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.MERGE)
    private UsuarioExt usuarioExt;

}
