package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.dtos.*;
import com.recetasAPD.recetasAPD.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    //Convertir de Usuario Entity a UsuarioResponseDto
    public UsuarioResponseDTO convertUsuarioToUsuarioResponseDTO(Usuario u){
        return modelMapper.map(u,UsuarioResponseDTO.class); //Primer parametro: Clase a convertir, Segundo parametro: A que clase la quiero convertir
    }

    public List<RecetaDTO> convertListRecetasToRecetasDTO(List<Receta> r){
        List<RecetaDTO> recetas = new ArrayList<RecetaDTO>();
        for(Receta aux : r){
            recetas.add(modelMapper.map(aux,RecetaDTO.class));
        }
        return recetas;
    }


    public RecetaDTO convertRecetaToRecetaDTO(Receta r){
        return modelMapper.map(r,RecetaDTO.class);
    }

    public IngredienteResponse convertIngredienteToIngredienteResponse(Ingrediente i){return modelMapper.map(i,IngredienteResponse.class);}

    public List<IngredienteResponse> convertIngredienteToIngredienteResponse(List<Ingrediente> i){
        return i
                .stream()
                .map(ingrediente -> modelMapper.map(ingrediente, IngredienteResponse.class))
                .collect(Collectors.toList());
    }

    public List<ItemIngrediente> convertItemIngredienteRequestListToItemIngredienteList(List<ItemIngredienteRequest> itemIngredientes){
        return itemIngredientes
                .stream()
                .map(item -> modelMapper.map(item, ItemIngrediente.class))
                .collect(Collectors.toList());
    }

    public ItemIngrediente convertItemIngredienteRequestToItemIngrediente(ItemIngredienteRequest item){
        return modelMapper.map(item,ItemIngrediente.class);
    }

    public List<ItemIngredienteResponse> convertItemIngredienteToItemIngredienteResponse(List<ItemIngrediente> ingredientes){
        return ingredientes.stream()
                .map(i -> modelMapper.map(i,ItemIngredienteResponse.class))
                .collect(Collectors.toList());
    }

    public ItemIngredienteResponse convertItemIngredienteToItemIngredienteResponse(ItemIngrediente i){
        return modelMapper.map(i, ItemIngredienteResponse.class);
    }

    public RecetaResponse convertRecetaToRecetaResponse(Receta r){
        RecetaResponse recetaResponse = modelMapper.map(r,RecetaResponse.class);
        return recetaResponse;
    }

    public Receta convertRecetaRequestToReceta(RecetaRequest r){
        return modelMapper.map(r,Receta.class);
    }

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
