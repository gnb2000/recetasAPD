package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad,Integer> {

}
