package com.recetasAPD.recetasAPD.services.UnidadService;

import com.recetasAPD.recetasAPD.entities.Unidad;
import com.recetasAPD.recetasAPD.exceptions.UnidadNotFoundException;
import com.recetasAPD.recetasAPD.repositories.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnidadServiceImpl implements UnidadService{

    @Autowired
    private UnidadRepository unidadRepository;

    @Override
    public void save(Unidad u) {
        unidadRepository.save(u);
    }

    @Override
    public void update(Unidad u) {
        unidadRepository.save(u);
    }

    @Override
    public Unidad findById(Integer id) {
        Optional<Unidad> u = unidadRepository.findById(id);
        if (u.isPresent()){
            return u.get();
        }
        throw new UnidadNotFoundException("No existe una unidad con ID "+ id);
    }
}
