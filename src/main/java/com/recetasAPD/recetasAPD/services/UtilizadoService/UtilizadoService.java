package com.recetasAPD.recetasAPD.services.UtilizadoService;

import com.recetasAPD.recetasAPD.dtos.UtilizadoRequest;
import com.recetasAPD.recetasAPD.entities.Utilizado;

import java.util.List;

public interface UtilizadoService {

    void save (Utilizado utilizado);
    void update(Utilizado utilizado);
    void delete(Utilizado utilizado);
    Utilizado addIngredienteToReceta(UtilizadoRequest utilizadoRequest);
    List<Utilizado> getItemIngredientesByReceta(Integer idReceta);
}
