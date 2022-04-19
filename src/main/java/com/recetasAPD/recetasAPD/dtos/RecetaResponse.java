package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaResponse {

    private Integer idReceta;
    private UsuarioResponseDTO usuario;
    private String titulo;
    private Integer porciones;
    private TipoResponse tipo;
    private Integer cantidadPersonas;
    private Integer estado;
    private String descripcion;
    private LocalDateTime fecha;
    private List<FotoResponse> galeria;
    private List<ItemIngredienteResponse> ingredientes;
    private List<PasoResponse> pasos;
}
