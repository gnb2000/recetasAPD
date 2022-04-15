package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.entities.Receta;

import java.util.List;

public interface RecetaService {
    void save(Receta receta);
    void update(Receta receta);
    void delete(Receta receta);
    List<Receta> getAll();
    List<Receta> findByTitulo(String nombre, Integer orden);
}
