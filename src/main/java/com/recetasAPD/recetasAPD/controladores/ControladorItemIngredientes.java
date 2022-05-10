package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.UtilizadoRequest;
import com.recetasAPD.recetasAPD.dtos.UtilizadoResponse;
import com.recetasAPD.recetasAPD.services.UtilizadoService.UtilizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorItemIngredientes {

    @Autowired
    private UtilizadoService utilizadoService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @PostMapping("/itemIngredientes")
    public ResponseEntity<UtilizadoResponse> nuevoItemIngrediente(@RequestBody UtilizadoRequest utilizadoRequest){
        return new ResponseEntity<>(entityDtoConverter.convertItemIngredienteToItemIngredienteResponse(utilizadoService.addIngredienteToReceta(utilizadoRequest)), HttpStatus.OK);
    }

    //LISTAR LOS DE UNA RECETA
    @GetMapping("/itemIngredientes/{idReceta}")
    public ResponseEntity<List<UtilizadoResponse>> getItemIngredientesByReceta(@PathVariable Integer idReceta){
        return new ResponseEntity<>(entityDtoConverter.convertItemIngredienteToItemIngredienteResponse(utilizadoService.getItemIngredientesByReceta(idReceta)),HttpStatus.OK);
    }

}
