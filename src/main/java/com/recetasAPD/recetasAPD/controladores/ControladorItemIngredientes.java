package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.ItemIngredienteRequest;
import com.recetasAPD.recetasAPD.dtos.ItemIngredienteResponse;
import com.recetasAPD.recetasAPD.entities.ItemIngrediente;
import com.recetasAPD.recetasAPD.services.ItemIngredienteService.ItemIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorItemIngredientes {

    @Autowired
    private ItemIngredienteService itemIngredienteService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @PostMapping("/itemIngredientes")
    public ResponseEntity<ItemIngredienteResponse> nuevoItemIngrediente(@RequestBody ItemIngredienteRequest itemIngredienteRequest){
        return new ResponseEntity<>(entityDtoConverter.convertItemIngredienteToItemIngredienteResponse(itemIngredienteService.addIngredienteToReceta(itemIngredienteRequest)), HttpStatus.OK);
    }

    //LISTAR LOS DE UNA RECETA
    @GetMapping("/itemIngredientes/{idReceta}")
    public ResponseEntity<List<ItemIngredienteResponse>> getItemIngredientesByReceta(@PathVariable Integer idReceta){
        return new ResponseEntity<>(entityDtoConverter.convertItemIngredienteToItemIngredienteResponse(itemIngredienteService.getItemIngredientesByReceta(idReceta)),HttpStatus.OK);
    }

}
