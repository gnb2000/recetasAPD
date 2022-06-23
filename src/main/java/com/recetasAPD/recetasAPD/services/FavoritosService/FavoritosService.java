package com.recetasAPD.recetasAPD.services.FavoritosService;

import com.recetasAPD.recetasAPD.entities.Favorita;
import com.recetasAPD.recetasAPD.entities.Ingrediente;

public interface FavoritosService {

    void save(Favorita favorita);
    void update(Favorita favorita);
    void delete(Favorita favorita);
}
