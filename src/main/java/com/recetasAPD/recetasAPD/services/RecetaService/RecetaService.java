package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecetaService {
    void save(Receta receta);
    void update(Receta receta);
    void delete(Receta receta);
    List<Receta> getAll();
    List<Receta> findByTitulo(String nombre, Integer orden);
    Receta getLast();
    Receta existeRecetaByNombreAndTitulo(String nombre, Integer idUsuario);
    Receta crearRecetaByNombreAndTitulo(String nombre, Integer idUsuario);
    Receta updateRecetaFieldsOnCreation(RecetaRequest receta, List<MultipartFile> fotos);
    Receta findById(Integer id);
    Receta generarRecetaConDistintasPorciones(RecetaRequest receta,Integer cantidadPorciones); // El usuario elige la cantidad de porciones q quiere.
    Receta generarRecetaConDistintasCantidades(RecetaRequest receta, String multiplo, Usuario usuario); // El usuario elige si quiere la cantidad o el doble de la receta.
    Receta generarRecetaConDistintaCantidadIngrediente(RecetaRequest receta,String ingrediente, Integer cantidad); // El usuario ingresa la cantidad de un ingrediente y a partir de eso calculo el resto.
}
