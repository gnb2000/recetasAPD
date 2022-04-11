package com.recetasAPD.recetasAPD.services;

import com.recetasAPD.recetasAPD.entities.Usuario;

public interface UsuarioService {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Usuario usuario);
    Usuario findByNicknameAndPassword(String nickname, String password) throws Exception;
    boolean registerNewUser(String nickname, String mail);
}
