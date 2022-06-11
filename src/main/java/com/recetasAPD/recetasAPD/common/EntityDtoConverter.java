package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.dtos.*;
import com.recetasAPD.recetasAPD.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    //Convertir de Usuario Entity a UsuarioResponseDto
    public UsuarioResponseDTO convertUsuarioToUsuarioResponseDTO(Usuario u){
        return
                modelMapper.map(u,UsuarioResponseDTO.class); //Primer parametro: Clase a convertir, Segundo parametro: A que clase la quiero convertir
    }

    public List<RecetaResponse> convertListRecetasToRecetasDTO(List<Receta> r){
        List<RecetaResponse> recetas = new ArrayList<RecetaResponse>();
        for(Receta aux : r){
            recetas.add(modelMapper.map(aux,RecetaResponse.class));
        }
        return recetas;
    }


    public IngredienteResponse convertIngredienteToIngredienteResponse(Ingrediente i){return modelMapper.map(i,IngredienteResponse.class);}

    public List<IngredienteResponse> convertIngredienteToIngredienteResponse(List<Ingrediente> i){
        return i
                .stream()
                .map(ingrediente -> modelMapper.map(ingrediente, IngredienteResponse.class))
                .collect(Collectors.toList());
    }

    public List<Utilizado> convertItemIngredienteRequestListToItemIngredienteList(List<UtilizadoRequest> itemIngredientes){
        return itemIngredientes
                .stream()
                .map(item -> modelMapper.map(item, Utilizado.class))
                .collect(Collectors.toList());
    }

    public Utilizado convertItemIngredienteRequestToItemIngrediente(UtilizadoRequest item){
        return modelMapper.map(item, Utilizado.class);
    }

    public List<UtilizadoResponse> convertItemIngredienteToItemIngredienteResponse(List<Utilizado> ingredientes){
        return ingredientes.stream()
                .map(i -> modelMapper.map(i, UtilizadoResponse.class))
                .collect(Collectors.toList());
    }

    public UtilizadoResponse convertItemIngredienteToItemIngredienteResponse(Utilizado i){
        return modelMapper.map(i, UtilizadoResponse.class);
    }

    public RecetaResponse convertRecetaToRecetaResponse(Receta r){
        RecetaResponse recetaResponse = modelMapper.map(r,RecetaResponse.class);
        return recetaResponse;
    }

    public Receta convertRecetaRequestToReceta(RecetaRequest r){
        return modelMapper.map(r,Receta.class);
    }

    public RecetaRequest converRecetaToRecetaRequest(Receta r) {return modelMapper.map(r,RecetaRequest.class); }

    public TipoResponse convertTipoToTipoResponse(Tipo tipo){
        return modelMapper.map(tipo,TipoResponse.class);
    }

    public List<Paso> convertPasoToPasoResponse(List<PasoRequest> pasos){
        return pasos
                .stream()
                .map(paso -> modelMapper.map(paso,Paso.class))
                .collect(Collectors.toList());
    }

    public List<PasoResponse> convertPasoRequestToPaso(List<Paso> pasos){
        return pasos
                .stream()
                .map(paso -> modelMapper.map(paso,PasoResponse.class))
                .collect(Collectors.toList());
    }

    public CalificacionResponse convertCalificacionToCalificacionResponse(Calificacion calificacion){
        return modelMapper.map(calificacion,CalificacionResponse.class);
    }


    public FotoResponse convertFotoToFotoResponse(Foto foto){
        return modelMapper.map(foto, FotoResponse.class);
    }

    public List<FotoResponse> convertFotoToFotoResponse(List<Foto> fotos){
        return fotos.stream()
                .map(foto -> modelMapper.map(foto,FotoResponse.class))
                .collect(Collectors.toList());
    }

    public UnidadResponse convertUnidadToUnidadResponse(Unidad u){
        return modelMapper.map(u,UnidadResponse.class);
    }

    public PasoResponse convertPasoToPasoResponse(Paso p){return modelMapper.map(p,PasoResponse.class);}

    public List<MultimediaResponse> convertMultimediaToMultimediaResponse(List<Multimedia> multimedia){
        return multimedia.stream()
                .map(m -> modelMapper.map(m,MultimediaResponse.class))
                .collect(Collectors.toList());
    }

    public List<RecetaResponse> convertRecetaToRecetaResponse(List<Receta> recetas){
        return recetas.stream()
                .map(receta -> modelMapper.map(receta,RecetaResponse.class))
                .collect(Collectors.toList());
    }







}
