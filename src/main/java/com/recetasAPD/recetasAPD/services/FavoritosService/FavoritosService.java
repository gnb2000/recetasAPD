package com.recetasAPD.recetasAPD.services.FavoritosService;

import com.recetasAPD.recetasAPD.entities.Favorita;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;

import java.util.List;

public interface FavoritosService{

    void save(Favorita favorita);
    void update(Favorita favorita);
    void delete(Favorita favorita);
    void addFavorita(Usuario usuario, Receta receta);
    List<Favorita> getFavoritasByUsuario(Usuario usuario);
    boolean isFavorita(Usuario usuario,Receta receta);
    void eliminarFavorita(Usuario usuario, Receta receta);
}
