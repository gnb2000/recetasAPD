package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.RecetaDTO;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.dtos.RecetaResponse;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.apache.coyote.Response;
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

   /* @PostMapping(value = "/receta",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public RecetaResponse addReceta(@RequestPart("receta") RecetaRequest receta, @RequestPart("fotos") List<MultipartFile> fotos, @RequestPart("multimediaPasos") List<List<MultipartFile>> multimediaPasos){
        //ArrayList<ArrayList<MultipartFile>> graph = new ArrayList<>();
        return entityDtoConverter.convertRecetaToRecetaResponse(recetaService.addReceta(receta,fotos,multimediaPasos));
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

    //Nuevo cargar receta

    @PostMapping("/recetas/check/{nombre}/{idUsuario}")
    public ResponseEntity<RecetaResponse> checkRecetaNombrePlato(@PathVariable String nombre, @PathVariable Integer idUsuario){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.existeRecetaByNombreAndTitulo(nombre,idUsuario)), HttpStatus.OK );
    }


    @PostMapping("/recetas/create/with/{nombre}/{idUsuario}")
    public ResponseEntity<RecetaResponse> crearRecetaByNombreAndUsuario(@PathVariable String nombre, @PathVariable Integer idUsuario){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.crearRecetaByNombreAndTitulo(nombre,idUsuario)), HttpStatus.OK );
    }

    @PutMapping(value="/recetas/update/fields", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RecetaResponse> updateRecetaFields(@RequestPart RecetaRequest receta, @RequestPart List<MultipartFile> fotos){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.updateRecetaFieldsOnCreation(receta,fotos)), HttpStatus.OK);
    }






}
