package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String mail;
    private String nickname;
    private boolean habilitado;
    private String nombre;
    private String avatar; // ver esto
    private Integer tipoUsuario;

}
