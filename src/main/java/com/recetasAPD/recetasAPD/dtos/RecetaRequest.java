package com.recetasAPD.recetasAPD.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecetaRequest {

    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private Integer porciones;
    private Integer cantPersonas;
    private Integer tipo; //Buscar el tipo por este ID
    private List<ItemIngredienteRequest> itemIngredientes;
    private List<PasoRequest> pasos;
}
