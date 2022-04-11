package com.recetasAPD.recetasAPD.services;


import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.repositories.UsuarioRepository;
import com.recetasAPD.recetasAPD.services.EmailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

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
        return Optional.ofNullable(usuarioRepository.findByNicknameAndContraseña(nickname,password))
                .orElseThrow( () -> new Exception("No existe un usuario con esos datos")); //Esta excepcion en realidad nosotros vamos a crear una especifica para esto
    }

    @Override
    public boolean registerNewUser(String nickname, String mail) {
        if ( !this.existeNicknameOrMail(nickname,mail) ){ //No existe un usuario con ese USUARIO / MAIL
            //Enviar mail al usuario
            Usuario u = new Usuario(mail,nickname);
            usuarioRepository.save(u);

            emailService.sendEmail(mail,"COMPLETAR DATOS","Aca va un LINK");

            return true;
        } else {
            return false;
        }
    }

    private boolean existeNicknameOrMail(String nickname, String mail) {
        return Optional.ofNullable(usuarioRepository.findByNicknameOrMail(nickname,mail))
                .isPresent();
    }


}
