package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.RecetaDTO;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.dtos.RecetaResponse;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorRecetas {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    /*@PostMapping(value = "/receta",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public RecetaResponse addReceta(@RequestPart("receta") RecetaRequest receta, @RequestPart("fotos") List<MultipartFile> fotos, @RequestPart("multimediaPasos") List<MultipartFile> multimediaPasos){
        //ArrayList<ArrayList<MultipartFile>> graph = new ArrayList<>();
        return entityDtoConverter.convertRecetaToRecetaResponse(recetaService.addReceta(receta,fotos));
    }*/




    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaDTO>> getAllrecetas() {
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

    @GetMapping("/recetas/{tipo}/{orden}")
    public ResponseEntity<List<RecetaDTO>> getRecetaTipo(@PathVariable(value= "tipo")String tipo,@PathVariable(value="orden")Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.findByTipo(tipo,orden)),HttpStatus.OK);
    }



}
