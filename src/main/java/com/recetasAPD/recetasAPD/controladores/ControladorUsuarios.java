package com.recetasAPD.recetasAPD.controladores;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControladorUsuarios {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/login/{nickname}/{password}")
    public ResponseEntity<Usuario> verificarLogIn(@PathVariable(value = "nickname") String nickname,@PathVariable(value = "contraseña") String contraseña) throws Exception{
        Usuario usuario = usuarioService.findByNickname(nickname);
        if (usuario.getContraseña().equals(contraseña)){
            return ResponseEntity.ok().body(usuario); // Falta convertir a DTO
        }else{
            throw new Exception("Los datos ingresados son incorrectos"); // Acá tendría que ver si devuelvo un DTO y un http o qué q pija pongo
        }
    }

}
