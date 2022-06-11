package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Calificacion;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository <Calificacion,Integer> {

    List<Calificacion> findByReceta(Receta receta);
    Calificacion findByUsuario(Usuario usuario);
}
