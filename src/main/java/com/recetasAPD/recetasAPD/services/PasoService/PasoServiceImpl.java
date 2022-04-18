package com.recetasAPD.recetasAPD.services.PasoService;

import com.recetasAPD.recetasAPD.entities.Paso;
import com.recetasAPD.recetasAPD.repositories.PasoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasoServiceImpl implements PasoService{

    @Autowired
    private PasoRepository pasoRepository;


    @Override
    public void save(Paso paso) {
        pasoRepository.save(paso);
    }

    @Override
    public void update(Paso paso) {
        pasoRepository.save(paso);
    }
}
