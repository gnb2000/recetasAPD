package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Integer> {

    List<Receta> findByTituloOrderByFechaAsc(String titulo);
    List<Receta> findByTitulo(String titulo);
    Receta findTop1ByOrderByFechaDesc(); // devuelve el primer resultado
    Receta findByTituloAndUsuario(String titulo, Usuario usuario);

    @Query("SELECT r FROM Receta r, ItemIngrediente i WHERE r.idReceta = i.idReceta AND i.ingrediente != ?1 GROUP BY r")
    List<Receta> getAllRecetaWithoutIngredient(Integer ingrediente);

}
