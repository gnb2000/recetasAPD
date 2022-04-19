package com.recetasAPD.recetasAPD.dtos;

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
    private String descripcion;
    private List<MultimediaResponse> multimedia;
}
