package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaRequest {

    private Integer tipo_contenido; //0 = Foto, 1 = Video
    private String multimedia; //Esto despues va a cambiar
}
