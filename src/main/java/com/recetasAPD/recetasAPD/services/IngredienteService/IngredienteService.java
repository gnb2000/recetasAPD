package com.recetasAPD.recetasAPD.services.IngredienteService;

import com.recetasAPD.recetasAPD.entities.Ingrediente;

import java.util.List;

public interface IngredienteService {

    void save(Ingrediente ingrediente);
    void update(Ingrediente ingrediente);
    void delete(Ingrediente ingrediente);
    Ingrediente findById(Integer id);
    List<Ingrediente> findAll();
    void addNewIngrediente(String nombre);

}
