package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Integer> {


}
