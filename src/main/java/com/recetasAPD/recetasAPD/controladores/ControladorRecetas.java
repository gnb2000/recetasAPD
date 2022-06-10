package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
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

   /* private static ControladorRecetas instancia;

    public ControladorRecetas() {
    }

    public static ControladorRecetas getInstance() {
        if (instancia == null)
            instancia = new ControladorRecetas();
        return instancia;
    }
    */
    @Autowired
    private RecetaService recetaService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;


    @GetMapping("/recetas")
    public ResponseEntity<List<RecetaResponse>> getAllrecetas() {
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/recetas/{nombre}/{orden}")
    public ResponseEntity<List<RecetaResponse>> getRecetasByNombre(@PathVariable(value="nombre")String nombre, @PathVariable(value = "orden")Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.findByTitulo(nombre,orden)),HttpStatus.OK);

    }

    @GetMapping("/recetas/last")
    public ResponseEntity<RecetaResponse> getLastReceta(){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.getLast()),HttpStatus.OK);
    }

    @GetMapping("/recetas/without/{ingrediente}/{orden}")
    public ResponseEntity<List<RecetaResponse>> getRecetaWithountIngredient(@PathVariable Integer ingrediente,@PathVariable Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.getRecetaWithoutIngrediente(ingrediente,orden)),HttpStatus.OK);
    }

    @GetMapping("/recetas/with/{ingrediente}/{orden}")
    public ResponseEntity<List<RecetaResponse>> getRecetaWithIngredient(@PathVariable Integer ingrediente,@PathVariable Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertListRecetasToRecetasDTO(recetaService.getRecetaWithIngrediente(ingrediente,orden)),HttpStatus.OK);
    }



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

    @GetMapping("/recetas/tipo/{tipo}/{orden}")
    public ResponseEntity<List<RecetaResponse>> getRecetasByTipo(@PathVariable Integer tipo, @PathVariable Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.findRecetaByTipo(tipo,orden)),HttpStatus.OK);
    }
    @PostMapping("recetas/proporciones/{idUsuario}/{proporcion}/{idReceta}")
    public ResponseEntity<RecetaResponse> proporcionReceta(@PathVariable Integer idUsuario,@PathVariable String proporcion,@PathVariable Integer idReceta){
        return new ResponseEntity<RecetaResponse>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.generarRecetaConDistintasCantidades(idReceta,proporcion,idUsuario)),HttpStatus.OK);
    }

    @GetMapping("/recetas/invitado/{invitado}/{orden}")
    public ResponseEntity<List<RecetaResponse>> getRecetasUsuario(@PathVariable Integer invitado, @PathVariable Integer orden){
        return new ResponseEntity<>(entityDtoConverter.convertRecetaToRecetaResponse(recetaService.findRecetasUsuario(invitado,orden)),HttpStatus.OK);
    }







}
