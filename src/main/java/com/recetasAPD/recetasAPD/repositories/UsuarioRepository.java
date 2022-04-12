package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findByNicknameAndPassword(String nickname, String password);
    Usuario findByMailAndPassword(String mail, String password);
    Usuario findByMail(String mail);
    Usuario findByNickname(String nickname);

}
