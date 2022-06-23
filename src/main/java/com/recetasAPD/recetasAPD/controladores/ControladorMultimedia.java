package com.recetasAPD.recetasAPD.controladores;

import com.recetasAPD.recetasAPD.services.MultimediaService.MultimediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ControladorMultimedia {

    @Autowired
    private MultimediaService  multimediaService;

    @PostMapping("/multimedia/uploadVideo")
    public ResponseEntity<String> uploadVideo(@RequestParam("video") MultipartFile video){
        //No se pueden cargar videos de mas de 100MB
        return new ResponseEntity<>(multimediaService.uploadVideo(video), HttpStatus.OK);
    }


}
