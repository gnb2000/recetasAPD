package com.recetasAPD.recetasAPD.services;


import com.recetasAPD.recetasAPD.entities.Calificacion;
import com.recetasAPD.recetasAPD.repositories.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public void save(Calificacion calificacion){calificacionRepository.save(calificacion);}

    @Override
    public void delete(Calificacion calificacion) {calificacionRepository.delete(calificacion);}

    @Override
    public void update(Calificacion calificacion) {calificacionRepository.save(calificacion);}
}
