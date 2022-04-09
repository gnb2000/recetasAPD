package com.recetasAPD.recetasAPD.services;


import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    //Dejemos este espacio entre lo de arriba y abajo
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void save(Usuario usuario) {

    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(Usuario usuario) {

    }

    @Override
    public Usuario findByNicknameAndPassword(String nickname, String password) throws Exception {
        return Optional.ofNullable(usuarioRepository.findByNicknameAndAndContraseña(nickname,password))
                .orElseThrow( () -> new Exception("No existe un usuario con esos datos")); //Esta excepcion en realidad nosotros vamos a crear una especifica para esto
    }


}
