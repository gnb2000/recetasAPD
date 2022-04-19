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

    @Query("SELECT distinct  Recetas.idReceta FROM Recetas r WHERE r.idReceta not in (SELECT ItemIngrediente.idReceta FROM ItemIngrediente WHERE ItemIngrediente.idIngrediente = ?1) ")
    List<Receta> getAllRecetaWithoutIngredient(Integer ingrediente);

}
