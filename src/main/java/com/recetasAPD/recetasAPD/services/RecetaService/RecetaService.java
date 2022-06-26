package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.Receta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecetaService {
    void save(Receta receta);
    void update(Receta receta);
    void delete(Receta receta);
    List<Receta> getAll();
    List<Receta> findByTitulo(String nombre, Integer orden);
    List<Receta> getRecetaWithoutIngrediente(String ingrediente,Integer orden);
    List<Receta> getRecetaWithIngrediente(String ingrediente, Integer orden);
    List<Receta> obtenerMejoresRecetas(Integer cantidad);
    Receta getLast();
    Receta existeRecetaByNombreAndTitulo(String nombre, Integer idUsuario);
    Receta crearRecetaByNombreAndTitulo(String nombre, Integer idUsuario);
    Receta updateRecetaFieldsOnCreation(RecetaRequest receta, List<MultipartFile> fotos);
    Receta findById(Integer id);
    Receta generarRecetaConDistintasPorciones(Integer idReceta,Integer cantidadPorciones,Integer idUsuario); // El usuario elige la cantidad de porciones q quiere.
    Receta generarRecetaConDistintasCantidades(Integer idReceta, String multiplo, Integer idUsuario); // El usuario elige si quiere la cantidad o el doble de la receta.
    Receta generarRecetaConDistintaCantidadIngrediente(Integer idReceta,Integer idUsuario, Integer idIngrediente, Integer cantidad,Integer idUnidad); // El usuario ingresa la cantidad de un ingrediente y a partir de eso calculo el resto.
    List<Receta> findRecetaByTipo(String idTipo, Integer orden);
    List<Receta>  findRecetasUsuario(String usuario, Integer orden);
    Float CalcularPuntuacionReceta(Integer idReceta);
}
