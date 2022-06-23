package com.recetasAPD.recetasAPD.services.TipoService;

import com.recetasAPD.recetasAPD.entities.Tipo;

import java.util.List;

public interface TipoService {

    Tipo findById(Integer id);
    List<Tipo> findAll();
}
