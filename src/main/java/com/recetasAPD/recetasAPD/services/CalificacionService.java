package com.recetasAPD.recetasAPD.services;


import com.recetasAPD.recetasAPD.entities.Calificacion;

public interface CalificacionService {
    void save(Calificacion clasificacion);
    void delete(Calificacion clasificacion);
    void update(Calificacion clasificacion);

}
