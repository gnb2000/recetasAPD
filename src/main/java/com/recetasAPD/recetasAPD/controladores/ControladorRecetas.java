package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.RecetaDTO;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorRecetas {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    public void CrearReceta(){
        Receta x = new Receta();
    }

    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaDTO>>getAllrecetas() {
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/recetas/{nombre}/{orden}")
        public ResponseEntity<List<RecetaDTO>> getRecetasByNombre(@PathVariable(value="nombre")String nombre, @PathVariable(value = "orden")Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.findByTitulo(nombre,orden)),HttpStatus.OK);

    }

    @GetMapping("/recetas/last")
    public ResponseEntity<RecetaDTO> getLastReceta(){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaDTO(recetaService.getLast()),HttpStatus.OK);
    }



}
