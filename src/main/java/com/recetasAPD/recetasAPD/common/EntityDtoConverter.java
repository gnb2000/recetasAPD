package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.dtos.FotoResponse;
import com.recetasAPD.recetasAPD.dtos.IngredienteResponse;
import com.recetasAPD.recetasAPD.dtos.RecetaDTO;
import com.recetasAPD.recetasAPD.dtos.UsuarioResponseDTO;
import com.recetasAPD.recetasAPD.entities.Foto;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
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

    public FotoResponse convertFotoToFotoResponse(Foto foto){
        return modelMapper.map(foto, FotoResponse.class);
    }





}
