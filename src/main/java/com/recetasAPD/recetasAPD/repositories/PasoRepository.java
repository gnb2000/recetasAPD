package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Paso;
import com.recetasAPD.recetasAPD.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasoRepository extends JpaRepository<Paso,Integer> {

    List<Paso> findByReceta(Receta r);

}
