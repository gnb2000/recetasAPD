package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.RecetaExt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaExtRepository extends JpaRepository<RecetaExt, Integer> {
}
