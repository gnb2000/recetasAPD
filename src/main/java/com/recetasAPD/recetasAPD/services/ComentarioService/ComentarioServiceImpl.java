package com.recetasAPD.recetasAPD.services.ComentarioService;

import com.recetasAPD.recetasAPD.entities.Comentario;
import com.recetasAPD.recetasAPD.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public void save(Comentario comentario){comentarioRepository.save(comentario);}

    @Override
    public void update(Comentario comentario) {comentarioRepository.save(comentario);}

    @Override
    public void delete(Comentario comentario) {comentarioRepository.delete(comentario);}




    }
