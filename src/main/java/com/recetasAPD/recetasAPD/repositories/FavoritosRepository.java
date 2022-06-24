package com.recetasAPD.recetasAPD.repositories;

import com.recetasAPD.recetasAPD.entities.Favorita;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface FavoritosRepository extends JpaRepository<Favorita,Integer> {
    boolean existsFavoritaByIdRecetaAndIdUsuario(Integer idReceta,Integer idUsuario);
    List<Favorita> findAllByIdUsuario(Integer idUsuario);
}
