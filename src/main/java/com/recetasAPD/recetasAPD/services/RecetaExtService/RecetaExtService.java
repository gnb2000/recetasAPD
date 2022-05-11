package com.recetasAPD.recetasAPD.services.RecetaExtService;


import com.recetasAPD.recetasAPD.entities.RecetaExt;

import java.util.List;

public interface RecetaExtService {

    void save(RecetaExt recetaExt);
    void update(RecetaExt recetaExt);
    void delete(RecetaExt recetaExt);
    List<RecetaExt> getAll();
}
