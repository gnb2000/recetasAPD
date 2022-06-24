package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {
    boolean existsIngredienteByNombre(String nombre);
}
