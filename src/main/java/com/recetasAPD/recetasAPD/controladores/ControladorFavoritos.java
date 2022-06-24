package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.FavoritaResponse;
import com.recetasAPD.recetasAPD.services.FavoritosService.FavoritosService;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
public class ControladorFavoritos {
    @Autowired
    private RecetaService recetaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FavoritosService favoritosService;
    @Autowired
    private EntityDtoConverter entityDtoConverter;


    @PostMapping("/favoritos/{idUsuario}/{idReceta}")
    public ResponseEntity<String> agregarFavoritaUsuario(@PathVariable Integer idUsuario, @PathVariable Integer idReceta){
        favoritosService.addFavorita(usuarioService.findById(idUsuario),recetaService.findById(idReceta));
        return new ResponseEntity<>("Receta agregada como favorita con exito", HttpStatus.OK);
    }

    @GetMapping("/favoritos/{idUsuario}")
    public ResponseEntity<List<FavoritaResponse>> getFavoritasUsuario(@PathVariable Integer idUsuario){
        return new ResponseEntity<>(entityDtoConverter.convertFavoritaToFavoritaResponse(favoritosService.getFavoritasByUsuario(usuarioService.findById(idUsuario))),HttpStatus.OK);
    }



}
