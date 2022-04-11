package com.recetasAPD.recetasAPD.services.IngredienteService;

import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServiceImpl implements IngredienteService{
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public void save(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
    }

    @Override
    public void update(Ingrediente ingrediente) {
        ingredienteRepository.save(ingrediente);
    }

    @Override
    public void delete(Ingrediente ingrediente) {
        ingredienteRepository.delete(ingrediente);
    }



}
