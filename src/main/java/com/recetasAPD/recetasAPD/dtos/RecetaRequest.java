package com.recetasAPD.recetasAPD.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecetaRequest {

    private Integer idReceta; //Edita la receta -> Este ID viene con valor, Si hace una nuevo, no va a tener valor
    private Integer idUsuario;
    private String titulo;
    private String descripcion;
    private Integer porciones;
    private Integer cantidadPersonas;
    private Integer tipo; //Buscar el tipo por este ID
    private List<ItemIngredienteRequest> itemIngredientes;
    private List<PasoRequest> pasos;
}
