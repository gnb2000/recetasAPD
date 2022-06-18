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
    @Query("SELECT r from Receta r JOIN r.recetaExt rExt WHERE r.nombre = ?1 and rExt.estado = 2 ORDER BY rExt.fecha DESC")
    List<Receta> findByNombreOrderByFechaAsc(String titulo);
    List<Receta> findByNombre(String titulo);
    @Query(value = "select * from recetas inner join recetas_ext where recetas_ext.id_receta = recetas.id_receta ORDER BY recetas_ext.fecha LIMIT 1", nativeQuery = true)
    Receta findTop1ByOrderByFechaDesc();
    Receta findByNombreAndUsuario(String titulo, Usuario usuario);

    List<Receta> findByTipoOrderByNombre(Tipo tipo);

    @Query("SELECT r from Receta r JOIN r.recetaExt rExt WHERE r.tipo = ?1 and rExt.estado = 2 ORDER BY rExt.fecha DESC")
    List<Receta> findByTipoOrderByFecha(Tipo tipo);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta not in (SELECT i.receta FROM Utilizado i WHERE i.ingrediente = ?1) ORDER BY r.nombre ASC")
    List<Receta> getAllRecetaWithoutIngredientTitulo(Ingrediente ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta not in (SELECT i.receta FROM Utilizado i JOIN r.recetaExt rExt WHERE i.ingrediente = ?1) ORDER BY rExt.fecha DESC")
    List<Receta> getAllRecetaWithoutIngredientAntiguedad(Ingrediente ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta in (SELECT i.receta FROM Utilizado i WHERE i.ingrediente = ?1) ORDER BY r.nombre ASC")
    List<Receta> getAllRecetaWithIngredientTitulo(Ingrediente ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta in (SELECT i.receta FROM Utilizado i WHERE i.ingrediente = ?1) ORDER BY rExt.fecha DESC")
    List<Receta> getAllRecetaWithIngredientAntiguedad(Ingrediente ingrediente);


    @Query("SELECT r from Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.usuario = ?1 ORDER BY r.nombre ASC")
    List<Receta> findByUsuarioTipoOrderByNombre(Usuario user);

    @Query("SELECT r from Receta r JOIN r.recetaExt rExt WHERE r.usuario = ?1 and rExt.estado = 2 ORDER BY rExt.fecha DESC")
    List<Receta> findByUsuarioTipoOrderByFecha(Usuario user);
}
