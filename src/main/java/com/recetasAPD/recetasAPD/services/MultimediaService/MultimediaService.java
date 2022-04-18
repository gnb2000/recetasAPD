package com.recetasAPD.recetasAPD.services.MultimediaService;

import com.recetasAPD.recetasAPD.entities.Foto;
import com.recetasAPD.recetasAPD.entities.Multimedia;
import com.recetasAPD.recetasAPD.entities.Paso;
import org.springframework.web.multipart.MultipartFile;

public interface MultimediaService {

    void save(Multimedia multimedia);
    void update(Multimedia multimedia);
    void delete(Multimedia multimedia);
    String uploadAndSaveFile(MultipartFile photoOrVideo, Paso paso);
}
