package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Utilizado;
import com.recetasAPD.recetasAPD.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemIngredienteRepository extends JpaRepository<Utilizado,Integer> {

    List<Utilizado> findByReceta(Receta r);
}
