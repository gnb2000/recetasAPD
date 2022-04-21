package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Foto;
import com.recetasAPD.recetasAPD.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Integer> {

    List<Foto> findByReceta(Receta r);
}
