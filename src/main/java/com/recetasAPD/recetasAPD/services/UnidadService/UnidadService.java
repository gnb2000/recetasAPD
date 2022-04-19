package com.recetasAPD.recetasAPD.services.UnidadService;

import com.recetasAPD.recetasAPD.entities.Unidad;

public interface UnidadService {

    void save(Unidad u);
    void update(Unidad u);
    Unidad findById(Integer id);

}
