package com.recetasAPD.recetasAPD.services.PasoService;

import com.recetasAPD.recetasAPD.dtos.PasoRequest;
import com.recetasAPD.recetasAPD.entities.Multimedia;
import com.recetasAPD.recetasAPD.entities.Paso;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.repositories.PasoRepository;
import com.recetasAPD.recetasAPD.services.MultimediaService.MultimediaService;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasoServiceImpl implements PasoService{

    @Autowired
    private PasoRepository pasoRepository;

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private MultimediaService multimediaService;


    @Override
    public void save(Paso paso) {
        pasoRepository.save(paso);
    }

    @Override
    public void update(Paso paso) {
        pasoRepository.save(paso);
    }

    @Override
    public Paso addPasoToReceta(PasoRequest paso, List<MultipartFile> multimediaPaso) {
        Receta r = recetaService.findById(paso.getIdReceta());
        Paso p = Paso.builder()
                .texto(paso.getDescripcion())
                .receta(r)
                .nroPaso(paso.getNroPaso())
                .build();
        pasoRepository.save(p);

        List<Multimedia> multimedia = new ArrayList<>();
        for (MultipartFile m : multimediaPaso){
            multimedia.add(multimediaService.uploadAndSaveFile(m, p));
        }
        p.setMultimedia(multimedia);
        this.update(p);
        return p;
    }

    @Override
    public List<Paso> getPasosByReceta(Integer idReceta) {
        Receta r = recetaService.findById(idReceta);
        return pasoRepository.findByReceta(r);
    }
}
