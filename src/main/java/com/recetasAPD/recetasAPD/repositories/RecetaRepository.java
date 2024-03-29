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
    @Query("SELECT r from Receta r JOIN r.recetaExt rExt WHERE r.nombre LIKE %?1% and rExt.estado = 2 ORDER BY rExt.fecha ASC")
    List<Receta> findByNombreOrderByFechaAsc(String titulo);
    @Query("Select r from Receta r JOIN r.recetaExt rExt WHERE r.nombre LIKE %?1% and rExt.estado = 2 ORDER BY r.nombre ASC")
    List<Receta> findByNombre(String titulo);
    @Query(value = "select * from recetas inner join recetas_ext where recetas_ext.id_receta = recetas.id_receta ORDER BY recetas_ext.fecha LIMIT 1", nativeQuery = true)
    Receta findTop1ByOrderByFechaDesc();
    @Query(value = "select * from recetas inner join recetas_ext where recetas_ext.id_receta = recetas.id_receta and recetas_ext.estado = 3 and recetas.id_usuario LIKE %?1%",nativeQuery = true)
    List<Receta> findPersonalizadasByUsuario(Integer idUsuario);

    @Query(value = "SELECT * from recetas inner join recetas_ext WHERE recetas_ext.id_receta = recetas.id_receta and recetas_ext.estado = 2 and recetas.id_usuario LIKE %?1%",nativeQuery = true)
    List<Receta> findRecetasByUsuario(Integer idUsuario);
    @Query(value = "SELECT * from recetas inner join recetas_ext WHERE recetas_ext.id_receta = recetas.id_receta and recetas_ext.estado = 2 and recetas.id_usuario LIKE %?2% and recetas.nombre LIKE %?1%",nativeQuery = true)
    Receta findByNombreAndUsuario(String titulo, Integer idUsuario);

    @Query("SELECT r from Receta r JOIN r.recetaExt rExt JOIN r.tipo t WHERE t.descripcion LIKE %?1% and rExt.estado = 2 ORDER BY r.nombre ASC")
    List<Receta> findByTipoOrderByNombre(String tipo);

    @Query("SELECT r from Receta r JOIN r.recetaExt rExt JOIN r.tipo t WHERE t.descripcion LIKE %?1% and rExt.estado = 2 ORDER BY rExt.fecha ASC")
    List<Receta> findByTipoOrderByFecha(String tipo);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta not in (SELECT u.receta FROM Utilizado u JOIN u.ingrediente i WHERE i.nombre LIKE %?1%) ORDER BY r.nombre ASC")
    List<Receta> getAllRecetaWithoutIngredientTitulo(String ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta not in (SELECT u.receta FROM Utilizado u JOIN u.ingrediente i WHERE i.nombre LIKE %?1%) ORDER BY rExt.fecha ASC")
    List<Receta> getAllRecetaWithoutIngredientAntiguedad(String ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt JOIN r.usuario o WHERE rExt.estado = 2 and r.idReceta not in (SELECT u.receta FROM Utilizado u JOIN u.ingrediente i WHERE i.nombre LIKE %?1%) ORDER BY o.nickname ASC")
    List<Receta> getAllRecetaWithoutIngredientUsuario(String ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta in (SELECT u.receta FROM Utilizado u JOIN u.ingrediente i WHERE i.nombre LIKE %?1%) ORDER BY r.nombre ASC")
    List<Receta> getAllRecetaWithIngredientTitulo(String ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2 and r.idReceta in (SELECT u.receta FROM Utilizado u JOIN u.ingrediente i WHERE i.nombre LIKE %?1%) ORDER BY rExt.fecha ASC")
    List<Receta> getAllRecetaWithIngredientAntiguedad(String ingrediente);

    @Query("SELECT distinct r FROM Receta r JOIN r.recetaExt rExt JOIN r.usuario o WHERE rExt.estado = 2 and r.idReceta in (SELECT u.receta FROM Utilizado u JOIN u.ingrediente i WHERE i.nombre LIKE %?1%) ORDER BY o.nickname ASC")
    List<Receta> getAllRecetaWithIngredientUsuario(String ingrediente);

    @Query("SELECT r from Receta r JOIN r.recetaExt rExt JOIN r.usuario u WHERE rExt.estado = 2 and u.nickname LIKE %?1% ORDER BY r.nombre ASC")
    List<Receta> findByUsuarioTipoOrderByNombre(String user);

    @Query("SELECT r from Receta r JOIN r.recetaExt rExt JOIN r.usuario u WHERE u.nickname LIKE %?1% and rExt.estado = 2 ORDER BY rExt.fecha DESC")
    List<Receta> findByUsuarioTipoOrderByFecha(String user);


    @Query("SELECT r from Receta r JOIN r.recetaExt rExt WHERE rExt.estado = 2")
    List<Receta> findAllRecetasWithoutProporciones();

}
