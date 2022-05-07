package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query("SELECT u FROM Usuario u JOIN u.usuarioExt uExt WHERE u.nickname = ?1 AND uExt.password = ?2")
    Usuario findByNicknameAndPassword(String nickname, String password);
    @Query("SELECT u FROM Usuario u JOIN u.usuarioExt uExt WHERE u.mail = ?1 AND uExt.password = ?2")
    Usuario findByMailAndPassword(String mail, String password);
    Usuario findByMail(String mail);
    Usuario findByNickname(String nickname);

}
