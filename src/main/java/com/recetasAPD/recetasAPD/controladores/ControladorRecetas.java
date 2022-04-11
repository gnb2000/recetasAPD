package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorRecetas {

    @Autowired
    private RecetaService recetaService;

    public void CrearReceta(){
        Receta x = new Receta();

    }

}
