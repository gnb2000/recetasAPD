package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Favorita;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritosRepository extends JpaRepository<Favorita,Integer> {
    boolean existsByRecetaAndUsuario(Receta receta, Usuario usuario);
    List<Favorita> findAllByUsuario(Usuario usuario);
    Favorita findByUsuarioAndReceta(Usuario usuario, Receta receta);
}
