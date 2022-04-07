package com.recetasAPD.recetasAPD.services;


import com.recetasAPD.recetasAPD.entities.Foto;
import com.recetasAPD.recetasAPD.repositories.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoServiceImpl implements FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    @Override
    public void save(Foto foto){ fotoRepository.save(foto);}

    @Override
    public void update(Foto foto) {
    fotoRepository.save(foto);
    }

    @Override
    public void delete(Foto foto) {
    fotoRepository.delete(foto);
    }
}
