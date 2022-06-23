package com.recetasAPD.recetasAPD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaResponse {

    private Integer idMultimedia;
    @JsonProperty("tipoContenido")
    private String tipo_contenido;
    private String urlContenido;
}
