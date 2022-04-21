package com.recetasAPD.recetasAPD.services.FotoService;

import com.recetasAPD.recetasAPD.entities.Foto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FotoService {

    void save(Foto foto);
    void update(Foto foto);
    void delete(Foto foto);
    List<Foto> getFotosByReceta(Integer idReceta);
    String uploadPhoto(MultipartFile photo);
    Foto savePhotoByUrlAndTitle(String url);
}
