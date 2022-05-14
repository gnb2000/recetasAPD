package com.recetasAPD.recetasAPD.controladores;
import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.CompleteRegisterRequest;
import com.recetasAPD.recetasAPD.dtos.UsuarioResponseDTO;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.services.EmailService.EmailService;

import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/usuario")
public class ControladorUsuarios {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;


    @GetMapping("/login/{mailOrNickname}/{password}")
    public ResponseEntity<UsuarioResponseDTO> verificarLogIn(@PathVariable(value = "nickname") String nickname, @PathVariable(value = "contraseña") String contraseña) throws Exception{
        Usuario usuario = usuarioService.findByNicknameAndPassword(nickname,contraseña);
        return new ResponseEntity<>(entityDtoConverter.convertUsuarioToUsuarioResponseDTO(usuario), HttpStatus.OK);
    }

    @PostMapping("/register/{usuario}/{mail}")
    public ResponseEntity<String> register(@PathVariable(value = "usuario") String usuario, @PathVariable(value = "mail") String mail){
        usuarioService.registerNewUser(usuario,mail);
        return new ResponseEntity<>("Usuario creado con exito, se ha enviado un correo al mail ingresado", HttpStatus.OK);

    }
    @PutMapping("/password/{idUsuario}/{password}")
    public ResponseEntity<String>updatePassword(@PathVariable(value="idUsuario")Integer idUsuario, @PathVariable(value ="password")String password){
        usuarioService.updatePassword(idUsuario,password);
        return new ResponseEntity<>("Contraseña modificada con exito", HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> getUserById(@PathVariable(value ="idUsuario") Integer idUsuario){
        Usuario u = usuarioService.findById(idUsuario);
        return new ResponseEntity<>(entityDtoConverter.convertUsuarioToUsuarioResponseDTO(u),HttpStatus.OK);
    }

    @PutMapping("/register")
    public ResponseEntity<String> completeRegistration(@RequestBody CompleteRegisterRequest request){
        Usuario u = usuarioService.findById(request.getIdUsuario());
        u.setNombre(request.getNombre());
        u.getUsuarioExt().setApellido(request.getApellido());
        u.getUsuarioExt().setPassword(request.getPassword());
        u.setHabilitado(true);
        usuarioService.update(u);
        return new ResponseEntity<>("Datos actualizados con exito",HttpStatus.OK);
    }

    @PutMapping("/account/recovery/{mail}")
    public ResponseEntity<Integer> accountRecovery(@PathVariable String mail){
        return new ResponseEntity<>(usuarioService.accountRecovery(mail),HttpStatus.OK);
    }

    @GetMapping("/check/code/{idUsuario}/{code}")
    public ResponseEntity<Boolean> checkRecoveryCode(@PathVariable Integer idUsuario, @PathVariable String code){
        return new ResponseEntity<>(usuarioService.checkRecoveryCode(idUsuario,code), HttpStatus.OK);
    }

    @PutMapping("/avatar/{idUsuario}")
    public ResponseEntity<String> updateAvatarByUsuario(@PathVariable Integer idUsuario, @RequestPart MultipartFile foto){
        usuarioService.updateAvatar(idUsuario,foto);
        return new ResponseEntity<>("Avatar actualizado con exito",HttpStatus.OK);
    }

    @PutMapping("/alias/{idUsuario}/{alias}")
    public ResponseEntity<String> updateAliasByUsuario(@PathVariable Integer idUsuario, @PathVariable String alias){
        usuarioService.updateAlias(idUsuario, alias);
        return new ResponseEntity<>("Alias actualizado con exito", HttpStatus.OK);
    }



}
