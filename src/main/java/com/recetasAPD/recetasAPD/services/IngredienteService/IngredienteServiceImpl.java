package com.recetasAPD.recetasAPD.services.IngredienteService;

import com.recetasAPD.recetasAPD.dtos.ItemIngredienteRequest;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.exceptions.IngredienteAlreadyCreatedException;
import com.recetasAPD.recetasAPD.exceptions.IngredienteNotFoundException;
import com.recetasAPD.recetasAPD.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        if (ingredienteRepository.findById(id).isPresent()){
            return ingredienteRepository.findById(id).get();
        }
        throw new IngredienteNotFoundException("No se encontro un Ingrediente con el ID ingresado");
    }

    @Override
    public List<Ingrediente> findAll() {
        List<Ingrediente> i = ingredienteRepository.findAll();
        if(i.size() != 0) {
            return i;
        }
        throw new IngredienteNotFoundException("No se encontraron ingredientes en el sistema");
    }

    @Override
    public void addNewIngrediente(String nombre) {
        //Verificamos si existe un ingrediente con ese nombre;
        if (!ingredienteRepository.existsIngredienteByNombre(nombre)) {
            Ingrediente i = new Ingrediente(nombre);
            ingredienteRepository.save(i);
        } else {
            throw new IngredienteAlreadyCreatedException("Ya existe un ingrediente con ese nombre");
        }


    }


}
