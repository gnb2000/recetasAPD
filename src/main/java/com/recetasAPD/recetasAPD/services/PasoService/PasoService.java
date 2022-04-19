package com.recetasAPD.recetasAPD.services.PasoService;

import com.recetasAPD.recetasAPD.dtos.PasoRequest;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.Paso;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PasoService {

    void save(Paso paso);
    void update(Paso paso);
    Paso addPasoToReceta(PasoRequest paso, List<MultipartFile> multimediaPaso);
    List<Paso> getPasosByReceta(Integer idReceta);
}
