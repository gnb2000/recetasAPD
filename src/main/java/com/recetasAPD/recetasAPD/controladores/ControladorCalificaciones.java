package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.CalificacionResponse;
import com.recetasAPD.recetasAPD.services.CalificacionService.CalificacionService;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControladorCalificaciones {

    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @PostMapping("/calificacion/nueva/{calificacion}/{idReceta}/{idUsuario}/{comentario}")
    public ResponseEntity<CalificacionResponse>crearPuntuacion(@PathVariable Integer calificacion,@PathVariable Integer idReceta,@PathVariable Integer idUsuario,@PathVariable String comentario){
        return new ResponseEntity<>(entityDtoConverter.convertCalificacionToCalificacionResponse(calificacionService.crearCalificacion(calificacion,idReceta,idUsuario,comentario)), HttpStatus.OK);
    }
    @GetMapping("/calificacion/{idReceta}")
    public Integer obtenerClasificacionPromedio(@PathVariable Integer idReceta){
        return recetaService.CalcularPuntuacionReceta(idReceta);
    }

}
