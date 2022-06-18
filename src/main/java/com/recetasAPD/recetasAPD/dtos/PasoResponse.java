package com.recetasAPD.recetasAPD.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasoResponse {

    private Integer idPaso;
    private Integer nroPaso;
    @JsonProperty("descripcion")
    private String texto;
    private List<MultimediaResponse> multimedia;
}
