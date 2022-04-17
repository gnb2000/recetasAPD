package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaRequest {

    private Integer idUsuario;
    private String nombre;
    private String descripcion;
    private String foto; //Esto despues va a cambiar
    private Integer porciones;
    private Integer cantPersonas;
    private Integer tipo; //Buscar el tipo por este ID
    private List<ItemIngredienteRequest> ItemIngredientes;
    private List<PasoRequest> pasos;
}
