package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasoRequest {

    private Integer nroPaso;
    private String texto;
    private MultimediaRequest multimedia;
}
