package com.recetasAPD.recetasAPD.services.MultimediaService;


import com.recetasAPD.recetasAPD.entities.Multimedia;
import com.recetasAPD.recetasAPD.repositories.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultimediaServiceImpl implements MultimediaService{
    @Autowired
    private MultimediaRepository multimediaRepository;

    @Override
    public void save(Multimedia multimedia) {
        multimediaRepository.save(multimedia);
    }

    @Override
    public void update(Multimedia multimedia) {
        multimediaRepository.save(multimedia);
    }

    @Override
    public void delete(Multimedia multimedia) {
        multimediaRepository.delete(multimedia);
    }
}
