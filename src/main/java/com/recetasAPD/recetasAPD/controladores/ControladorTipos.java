package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.TipoResponse;
import com.recetasAPD.recetasAPD.services.TipoService.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tipo")
public class ControladorTipos {

    @Autowired
    private TipoService tipoService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @GetMapping("/")
    public ResponseEntity<List<TipoResponse>> getAllTipos(){
        return new ResponseEntity<>(entityDtoConverter.convertTipoToTipoResponse(tipoService.findAll()), HttpStatus.OK);
    }
}
