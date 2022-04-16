package com.recetasAPD.recetasAPD.services.IngredienteService;

import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.exceptions.IngredienteNotFoundException;
import com.recetasAPD.recetasAPD.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Ingrediente findById(Integer id) {
        return Optional.ofNullable(ingredienteRepository.findById(id).get())
                .orElseThrow(() -> new IngredienteNotFoundException("No se encontro un Ingrediente con el ID ingresado"));
    }

    @Override
    public List<Ingrediente> findAll() {
        List<Ingrediente> i = ingredienteRepository.findAll();
        if(i.size() != 0) {
            return i;
        }
        throw new IngredienteNotFoundException("No se encontraron ingredientes en el sistema");
    }


}
