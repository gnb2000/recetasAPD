package com.recetasAPD.recetasAPD.services.FotoService;

import com.recetasAPD.recetasAPD.entities.Foto;
import org.springframework.web.multipart.MultipartFile;

public interface FotoService {

    void save(Foto foto);
    void update(Foto foto);
    void delete(Foto foto);
    String uploadPhoto(MultipartFile photo);
    Foto savePhotoByUrlAndTitle(String url);
}
