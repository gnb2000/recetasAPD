package com.recetasAPD.recetasAPD.controladores;
import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.UsuarioResponseDTO;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControladorUsuarios {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;


    @GetMapping("/login/{nickname}/{password}")
    public ResponseEntity<UsuarioResponseDTO> verificarLogIn(@PathVariable(value = "nickname") String nickname, @PathVariable(value = "contraseña") String contraseña) throws Exception{
        Usuario usuario = usuarioService.findByNicknameAndPassword(nickname,contraseña);
        return new ResponseEntity<>(entityDtoConverter.convertUsuarioToUsuarioResponseDTO(usuario), HttpStatus.OK);

    }

}
