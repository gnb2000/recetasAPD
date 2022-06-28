package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritaResponse {

    private Integer idFavorita;
    private RecetaResponse receta;
    private UsuarioResponseDTO usuario;
    private boolean estado;


}
