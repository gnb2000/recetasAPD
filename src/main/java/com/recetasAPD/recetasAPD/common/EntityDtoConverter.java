package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.dtos.UsuarioResponseDTO;
import com.recetasAPD.recetasAPD.entities.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    //Convertir de Usuario Entity a UsuarioResponseDto
    public UsuarioResponseDTO convertUsuarioToUsuarioResponseDTO(Usuario u){
        return modelMapper.map(u,UsuarioResponseDTO.class); //Primer parametro: Clase a convertir, Segundo parametro: A que clase la quiero convertir
    }

    //Ustedes lo unico que van a tener que agregar son los metodos como arriba





}
