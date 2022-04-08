package com.recetasAPD.recetasAPD.services;


import com.recetasAPD.recetasAPD.entities.Comentario;

public interface ComentarioService {
    void save(Comentario comentario);
    void update(Comentario comentario);
    void delete(Comentario comentario);
 }
