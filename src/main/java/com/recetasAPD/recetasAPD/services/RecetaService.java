package com.recetasAPD.recetasAPD.services;

import com.recetasAPD.recetasAPD.entities.Receta;

public interface RecetaService {
    void save(Receta receta);
    void update(Receta receta);
    void delete(Receta receta);
}
