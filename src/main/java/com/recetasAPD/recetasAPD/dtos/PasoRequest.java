package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasoRequest {

    private Integer nroPaso;
    private String descripcion;
    //private List<MultimediaRequest> galeria; ESPERAR A QUE SEPAMOS COMO HACER LO DE LAS FOTOS / VIDEOS
}
