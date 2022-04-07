package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Paso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasoRepository extends JpaRepository<Paso,Integer> {

}
