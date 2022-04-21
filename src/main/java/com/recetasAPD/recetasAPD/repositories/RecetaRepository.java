package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Tipo;
import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Integer> {

    //TODO todos ordenados por fehcha con @query
    List<Receta> findByNombreOrderByFechaAsc(String titulo);
    List<Receta> findByNombre(String titulo);
    Receta findTop1ByOrderByFechaDesc(); // devuelve el primer resultado
    Receta findByNombreAndUsuario(String titulo, Usuario usuario);
    List<Receta> findByTipoOrderByNombre(Tipo tipo);
    List<Receta> findByTipoOrderByFecha(Tipo tipo);

    @Query("SELECT distinct r FROM Receta r WHERE r.idReceta not in (SELECT i.receta FROM ItemIngrediente i WHERE i.ingrediente = ?1) ORDER BY r.nombre ASC")
    List<Receta> getAllRecetaWithoutIngredientTitulo(Ingrediente ingrediente);

    @Query("SELECT distinct r FROM Receta r WHERE r.idReceta not in (SELECT i.receta FROM ItemIngrediente i WHERE i.ingrediente = ?1) ORDER BY r.fecha ASC")
    List<Receta> getAllRecetaWithoutIngredientAntiguedad(Ingrediente ingrediente);

    @Query("SELECT distinct r FROM Receta r WHERE r.idReceta in (SELECT i.receta FROM ItemIngrediente i WHERE i.ingrediente = ?1) ORDER BY r.nombre ASC")
    List<Receta> getAllRecetaWithIngredientTitulo(Ingrediente ingrediente);

    @Query("SELECT distinct r FROM Receta r WHERE r.idReceta in (SELECT i.receta FROM ItemIngrediente i WHERE i.ingrediente = ?1) ORDER BY r.fecha ASC")
    List<Receta> getAllRecetaWithIngredientAntiguedad(Ingrediente ingrediente);


}
