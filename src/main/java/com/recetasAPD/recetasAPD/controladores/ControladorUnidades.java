package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.UnidadResponse;
import com.recetasAPD.recetasAPD.entities.Unidad;
import com.recetasAPD.recetasAPD.services.UnidadService.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidad")
public class ControladorUnidades {

    @Autowired
    private UnidadService unidadService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @GetMapping("/")
    public ResponseEntity<List<UnidadResponse>> getAllUnidades(){
        return new ResponseEntity<>(entityDtoConverter.convertUnidadToUnidadResponse(unidadService.getAllUnidades()), HttpStatus.OK);
    }
}
