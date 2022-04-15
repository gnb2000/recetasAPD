package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.exceptions.RecetasEmptyException;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecetaServiceImpl implements RecetaService{
    @Autowired // Sirve para inyectar lo del repositorio y para que funcione como singleton.
    private RecetaRepository recetaRepository;


    @Override
    public void save(Receta receta) {

        recetaRepository.save(receta);
    }

    @Override
    public void update(Receta receta) {
        recetaRepository.save(receta);
    }

    @Override
    public void delete(Receta receta) {
        recetaRepository.delete(receta);

    }

    @Override
    public List<Receta> getAll()  {
        if(recetaRepository.findAll().isEmpty()){
            throw new RecetasEmptyException("No tenemos recetas cargadas aun!");
        }
        return recetaRepository.findAll();
    }

    @Override
    public List<Receta> findByTitulo(String nombre, Integer orden) {
        if(!recetaRepository.findByTitulo(nombre).isEmpty()){
            if(orden == 1){
                return recetaRepository.findByTituloOrderByFechaAsc(nombre);
            }else {
                return recetaRepository.findByTitulo(nombre);
            }
        }else{
            throw new RecetasEmptyException("¡No hay recetas con ese nombre!");
        }
    }

    @Override
    public Receta getLast() {
        if(!recetaRepository.findAll().isEmpty()){
            return recetaRepository.findTop1ByOrderByFechaDesc();
        }else{
            throw new RecetasEmptyException("¡No hay recetas cargadas!");
        }
    }


}
