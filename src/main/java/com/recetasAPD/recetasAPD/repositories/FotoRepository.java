package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Integer> {
}
