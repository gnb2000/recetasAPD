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







}
