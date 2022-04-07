package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.ItemIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemIngredienteRepository extends JpaRepository<ItemIngrediente,Integer> {
}
