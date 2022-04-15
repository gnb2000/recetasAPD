package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Integer> {

    List<Receta> findByTituloOrderByFechaAsc(String titulo);
    List<Receta> findByTitulo(String titulo);

}
