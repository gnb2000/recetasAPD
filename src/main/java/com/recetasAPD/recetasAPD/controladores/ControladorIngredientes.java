package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.IngredienteResponse;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.services.IngredienteService.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingrediente")
public class ControladorIngredientes {

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @PostMapping("/{nombre}")
    public ResponseEntity<String> addIngrediente(@PathVariable String nombre){
        ingredienteService.addNewIngrediente(nombre);
        return new ResponseEntity<>("Ingrediente agregado con exito", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteResponse> findIngredienteById(@PathVariable Integer id){
        Ingrediente i = ingredienteService.findById(id);
        return new ResponseEntity<>(entityDtoConverter.convertIngredienteToIngredienteResponse(i),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IngredienteResponse>> findAllIngredientes(){
        return new ResponseEntity<>(entityDtoConverter.convertIngredienteToIngredienteResponse(ingredienteService.findAll()), HttpStatus.OK);
    }


}
