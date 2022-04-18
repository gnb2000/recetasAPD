package com.recetasAPD.recetasAPD.services.TipoService;

import com.recetasAPD.recetasAPD.entities.Tipo;
import com.recetasAPD.recetasAPD.exceptions.TipoNotFoundException;
import com.recetasAPD.recetasAPD.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoServiceImpl implements TipoService{

    @Autowired
    private TipoRepository tipoRepository;

    @Override
    public Tipo findById(Integer id) {
        Optional<Tipo> tipo = tipoRepository.findById(id);
        if (tipo.isPresent()){
            return tipo.get();
        }
        throw new TipoNotFoundException("No se encontro un Tipo con ese ID");
    }
}
