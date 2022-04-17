package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasoResponse {

    private Integer idPaso;
    private Integer nroPaso;
    private String texto;
    private String url_multimedia;
}
