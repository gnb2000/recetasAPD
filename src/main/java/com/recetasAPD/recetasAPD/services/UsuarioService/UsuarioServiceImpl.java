package com.recetasAPD.recetasAPD.services.UsuarioService;


import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.entities.UsuarioExt;
import com.recetasAPD.recetasAPD.exceptions.*;
import com.recetasAPD.recetasAPD.repositories.UsuarioExtRepository;
import com.recetasAPD.recetasAPD.repositories.UsuarioRepository;
import com.recetasAPD.recetasAPD.services.EmailService.EmailService;
import com.recetasAPD.recetasAPD.services.FotoService.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private FotoService fotoService;

    @Autowired
    private UsuarioExtRepository usuarioExtRepository;

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario findByNicknameAndPassword(String nickname, String password) throws Exception {
        return Optional.ofNullable(usuarioRepository.findByNicknameAndPassword(nickname,password))
                .orElseThrow( () -> new UserNotFoundException("No se ha encontrado un usuario con los datos ingresados"));
    }

    @Override
    public Usuario login(String nicknameOrMail, String password) {
        if (Optional.ofNullable(usuarioRepository.findByNicknameAndPassword(nicknameOrMail,password)).isPresent()){
            return usuarioRepository.findByNicknameAndPassword(nicknameOrMail,password);
        } else if (Optional.ofNullable(usuarioRepository.findByMailAndPassword(nicknameOrMail,password)).isPresent()){
            return usuarioRepository.findByMailAndPassword(nicknameOrMail,password);
        }
        throw new UserNotFoundException("No se ha encontrado un usuario con los datos ingresados");
    }

    @Override
    public Usuario findById(Integer idUsuario){
        if(usuarioRepository.findById(idUsuario).isPresent()){
            return usuarioRepository.findById(idUsuario).get();
        }
        throw new UserNotFoundException("Usuario NO encontrado");
    }

    @Override
    public void registerNewUser(String nickname, String mail) {
        //Verificamos si esta en uso el usuario o mail
        this.existeNicknameOrMail(nickname,mail);

        //Creamos el usuario y lo persistimos
        Usuario u = Usuario.builder()
                .mail(mail)
                .nickname(nickname)
                .build();

        usuarioRepository.save(u);

        UsuarioExt usuarioExt = UsuarioExt.builder().build();
        usuarioExt.setUsuario(u);
        usuarioExtRepository.save(usuarioExt);


        //Enviamos el correo
        emailService.sendEmail(mail,"CUENTA CREADA CON EXITO","Bienvenido a la comunidad de Cook Now. \n " +
                "\n" +
                "Su cuenta ha sido creada con exito. \n " +
                "Ingrese a la aplicacion para completar el resto de los datos de su cuenta.");
    }

    @Override
    public void updatePassword(Integer idUsuario, String password) {
        if(usuarioRepository.findById(idUsuario).isPresent()){
            Usuario u = usuarioRepository.findById(idUsuario).get();
            u.getUsuarioExt().setPassword(password);
            usuarioRepository.save(u);
        }else{
            throw new UserNotFoundException("No se encontro un usuario con este ID");
        }
    }

    @Override
    public Integer accountRecovery(String mail) {
        Optional<Usuario> u = Optional.ofNullable(usuarioRepository.findByMail(mail));

        if (u.isPresent()){
            if (u.get().isHabilitado()){
                String code = this.generateRecoveryCode();
                u.get().getUsuarioExt().setRecoveryCode(code);
                usuarioRepository.save(u.get());
                emailService.sendEmail(u.get().getMail(),"RECOVERY CODE","Este es tu codigo de recuperacion de cuenta : "+ code + ". Por favor ingresarlo en la aplicacion para recuperar su cuenta.");
                return u.get().getIdUsuario();
            } else {
                throw new IncompleteRegistrationException("Su cuenta tiene el proceso de registracion incompleto, completelo para poder recuperar la cuenta");
            }
        } else {
            throw new UserNotFoundException("No existe un usuario con el mail ingresado");
        }


    }

    @Override
    public boolean checkRecoveryCode(Integer idUsuario, String code) {
        Usuario u = this.findById(idUsuario);
        if (u.getUsuarioExt().getRecoveryCode().equals(code)){
            return true;
        }
        throw new IncorrectCodeRecoveryException(false);
    }

    @Override
    public String updateAvatar(Integer idUsuario, MultipartFile foto) {
        Usuario u = this.findById(idUsuario);
        String url = fotoService.uploadPhoto(foto);
        u.setAvatar(url);
        this.update(u);
        return url;
    }

    @Override
    public void updateAlias(Integer idUsuario, String alias) {
        Usuario u = this.findById(idUsuario);
        this.existeNicknameOrMail(alias,null);
        u.setNickname(alias);
        this.update(u);
    }

    @Override
    public Integer getIdUsuarioByEmail(String email) {
        return usuarioRepository.findByMail(email).getIdUsuario();
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
            if (usuarioRepository.findByMail(mail).isHabilitado()){
                //Proceso de registracion completo
                throw new AccountAlreadyCreatedException("Ya existe una cuenta registrada con el mail ingresado, se le enviara un correo al mail ya registrado para poder recuperar la clave");
            }
            throw new NotValidMailException("El mail esta asociado a una cuenta con el proceso de registracion incompleto, por favor contacte con soporte del sitio");
        }
        return false;
    }

    private String generateRecoveryCode(){
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }


}
