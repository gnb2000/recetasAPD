package com.recetasAPD.recetasAPD.services.CalificacionService;


import com.recetasAPD.recetasAPD.entities.Calificacion;

import java.util.List;

public interface CalificacionService {
    void save(Calificacion clasificacion);
    void delete(Calificacion clasificacion);
    void update(Calificacion clasificacion);
    Calificacion crearCalificacion(int idReceta,int idUsuario,Integer puntuacion,String comentario);
    List<Calificacion> obtenerCalificacionesPorReceta(Integer idReceta);
    boolean existeCalificacionUsuario(Integer idUsuario,Integer idReceta);
}
