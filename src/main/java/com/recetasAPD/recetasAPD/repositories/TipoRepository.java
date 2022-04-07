package com.recetasAPD.recetasAPD.repositories;


import com.recetasAPD.recetasAPD.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo,Integer> {
}
