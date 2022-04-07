package com.recetasAPD.recetasAPD.services;

import com.recetasAPD.recetasAPD.entities.Ingrediente;

public interface IngredienteService {
    void save(Ingrediente ingrediente);
    void update(Ingrediente ingrediente);
    void delete(Ingrediente ingrediente);

}
