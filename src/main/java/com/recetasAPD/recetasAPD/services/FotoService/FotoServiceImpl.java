package com.recetasAPD.recetasAPD.services.FotoService;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.recetasAPD.recetasAPD.entities.Foto;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.repositories.FotoRepository;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FotoServiceImpl implements FotoService {

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private Cloudinary cloudinary;

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

    @Override
    public List<Foto> getFotosByReceta(Integer idReceta) {
        Receta r = recetaService.findById(idReceta);
        return fotoRepository.findByReceta(r);
    }

    @Override
    public String uploadPhoto(MultipartFile photo) {
        try {
            File uploadedFile = convertMultiPartToFile(photo);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Foto savePhotoByUrlAndTitle(String url) {
        Foto foto = Foto.builder()
                .urlFoto(url)
                .build();
        return fotoRepository.save(foto);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
