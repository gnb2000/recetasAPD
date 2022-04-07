package com.recetasAPD.recetasAPD.services;

import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    }

    @Override
    public void delete(Receta receta) {

    }


}
