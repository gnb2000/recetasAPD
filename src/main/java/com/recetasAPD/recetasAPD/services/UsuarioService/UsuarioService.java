package com.recetasAPD.recetasAPD.services.UsuarioService;

import com.recetasAPD.recetasAPD.entities.Usuario;

public interface UsuarioService {
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(Usuario usuario);
    Usuario findByNicknameAndPassword(String nickname, String password) throws Exception;
    void registerNewUser(String nickname, String mail);
}
