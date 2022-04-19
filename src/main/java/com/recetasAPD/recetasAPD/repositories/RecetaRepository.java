package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Integer> {

    List<Receta> findByTituloOrderByFechaAsc(String titulo);
    List<Receta> findByTitulo(String titulo);
    Receta findTop1ByOrderByFechaDesc(); // devuelve el primer resultado

    @Query("SELECT r FROM Receta r, Tipo t WHERE t.idTipo = r.tipo AND t.descripcion = ?1")
    List<Receta> findByTipo(Tipo tipo);

    @Query("SELECT r FROM Receta r, Tipo t WHERE t.idTipo = r.tipo AND t.descripcion = ?1 ORDER BY r.titulo ASC")
    List<Receta> findByTipoAlfa(String tipo);

    @Query("SELECT r FROM Receta r, Tipo t WHERE t.idTipo = r.tipo AND t.descripcion = ?1 ORDER BY r.fecha DESC")
    List<Receta> findByTipoFecha(String tipo);
}
