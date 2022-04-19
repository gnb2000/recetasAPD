package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.PasoRequest;
import com.recetasAPD.recetasAPD.dtos.PasoResponse;
import com.recetasAPD.recetasAPD.services.PasoService.PasoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ControladorPasos {

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @Autowired
    private PasoService pasoService;

    @PostMapping(value = "/pasos/add",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PasoResponse> addPasoToReceta(@RequestPart PasoRequest paso, @RequestPart List<MultipartFile> multimediaPaso){
        return new ResponseEntity<>(entityDtoConverter.convertPasoToPasoResponse(pasoService.addPasoToReceta(paso, multimediaPaso)), HttpStatus.OK);
    }

    @GetMapping("/pasos/{idReceta}")
    public ResponseEntity<List<PasoResponse>> getPasosByIdReceta(@PathVariable  Integer idReceta){
        return new ResponseEntity<>(entityDtoConverter.convertPasoRequestToPaso(pasoService.getPasosByReceta(idReceta)),HttpStatus.OK);
    }
}
