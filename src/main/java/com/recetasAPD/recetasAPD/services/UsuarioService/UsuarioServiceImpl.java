package com.recetasAPD.recetasAPD.services.UsuarioService;


import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.exceptions.NotValidMailException;
import com.recetasAPD.recetasAPD.exceptions.NotValidNicknameException;
import com.recetasAPD.recetasAPD.repositories.UsuarioRepository;
import com.recetasAPD.recetasAPD.services.EmailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
    public void registerNewUser(String nickname, String mail) {
        //Verificamos si esta en uso el usuario o mail
        this.existeNicknameOrMail(nickname,mail);

        //Creamos el usuario y lo persistimos
        Usuario u = new Usuario(mail,nickname);
        usuarioRepository.save(u);

        //Enviamos el correo
        emailService.sendEmail(mail,"COMPLETAR DATOS","Aca va un LINK");
    }

    private boolean existeNicknameOrMail(String nickname, String mail) {
        if (Optional.ofNullable(usuarioRepository.findByNickname(nickname)).isPresent()){

            Random random = new Random();
            String posibleUsuario = nickname + random.nextInt(100);
            List<String> aliasRecomendados = new ArrayList<String>();
            //Agrego 3 alias recomendados
            for (int i = 0 ; i < 3 ; i++){
                while (Optional.ofNullable(usuarioRepository.findByNickname(posibleUsuario)).isPresent() || aliasRecomendados.contains(posibleUsuario)){
                    posibleUsuario += random.nextInt(100);
                }
                aliasRecomendados.add(posibleUsuario);
            }
            throw new NotValidNicknameException("El usuario ingresado ya esta en uso", aliasRecomendados);

        } else if (Optional.ofNullable(usuarioRepository.findByMail(mail)).isPresent()) {
            throw new NotValidMailException("Ya existe una cuenta registrada con el mail ingresado, se le enviara un correo al mail ya registrado para poder recuperar la clave");
        }
        return false;
    }


}
