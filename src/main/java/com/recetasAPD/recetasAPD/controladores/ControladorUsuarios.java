package com.recetasAPD.recetasAPD.controladores;
import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.CompleteRegisterRequest;
import com.recetasAPD.recetasAPD.dtos.UsuarioResponseDTO;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.services.EmailService.EmailService;

import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ControladorUsuarios {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;


    @GetMapping("/login/{nickname}/{password}")
    public ResponseEntity<UsuarioResponseDTO> verificarLogIn(@PathVariable(value = "nickname") String nickname, @PathVariable(value = "contraseña") String contraseña) throws Exception{
        Usuario usuario = usuarioService.findByNicknameAndPassword(nickname,contraseña);
        return new ResponseEntity<>(entityDtoConverter.convertUsuarioToUsuarioResponseDTO(usuario), HttpStatus.OK);
    }

    @PostMapping("/register/{usuario}/{mail}")
    public ResponseEntity<String> register(@PathVariable(value = "usuario") String usuario, @PathVariable(value = "mail") String mail){
        //TODO Verificar que valor darle por defecto a tipo_usuario y habilitado. Ademas, armar el endpoint para que rellene los datos
        usuarioService.registerNewUser(usuario,mail);
        return new ResponseEntity<>("Usuario creado con exito, se ha enviado un correo al mail ingresado para rellenar el resto de sus datos", HttpStatus.OK);

    }
    @PutMapping("/password/{idUsuario}/{password}")
    public ResponseEntity<String>updatePassword(@PathVariable(value="idUsuario")Integer idUsuario, @PathVariable(value ="password")String password){
        usuarioService.updatePassword(idUsuario,password);
        return new ResponseEntity<>("Contraseña modificada con exito", HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> getUserById(@PathVariable(value ="idUsuario") Integer idUsuario){
        Usuario u = usuarioService.findById(idUsuario);
        return new ResponseEntity<>(entityDtoConverter.convertUsuarioToUsuarioResponseDTO(u),HttpStatus.OK);
    }

    @PutMapping("/register")
    public ResponseEntity<String> completeRegistration(@RequestBody CompleteRegisterRequest request){
        Usuario u = usuarioService.findById(request.getIdUsuario());
        u.setAvatar(request.getAvatar());
        u.setNombre(request.getNombre());
        u.setHabilitado(true);
        usuarioService.update(u);
        return new ResponseEntity<>("Datos actualizados con exito",HttpStatus.OK);
    }

    @PutMapping("/account/recovery/{idUsuario}")
    public ResponseEntity<String> accountRecovery(@PathVariable Integer idUsuario){
        usuarioService.accountRecovery(idUsuario);
        return new ResponseEntity<>("Se ha enviado un codigo de 6 digitos a su mail",HttpStatus.OK);
    }



}
