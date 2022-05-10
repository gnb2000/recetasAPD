package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "usuarios_ext")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioExt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioExt;
    private String password;
    private String recoveryCode;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}
