package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.FotoResponse;
import com.recetasAPD.recetasAPD.entities.Foto;
import com.recetasAPD.recetasAPD.services.FotoService.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ControladorFotos {

    @Autowired
    private FotoService fotoService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @PostMapping("/photo")
    public ResponseEntity<FotoResponse> uploadPhoto(@RequestParam("photo") MultipartFile photo, @RequestParam("title") String title){
        String url = fotoService.uploadPhoto(photo);
        return new ResponseEntity<>(entityDtoConverter.convertFotoToFotoResponse(fotoService.savePhotoByUrlAndTitle(url,title)), HttpStatus.OK);
    }
}
