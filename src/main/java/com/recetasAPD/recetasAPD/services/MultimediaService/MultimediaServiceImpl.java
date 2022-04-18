package com.recetasAPD.recetasAPD.services.MultimediaService;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.recetasAPD.recetasAPD.entities.Multimedia;
import com.recetasAPD.recetasAPD.entities.Paso;
import com.recetasAPD.recetasAPD.repositories.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class MultimediaServiceImpl implements MultimediaService{

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Autowired
    private Cloudinary cloudinary;

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

    @Override
    public String uploadAndSaveFile(MultipartFile photoOrVideo, Paso paso) {
        try {
            File uploadedFile = convertMultiPartToFile(photoOrVideo);
            Map uploadResult;
            String tipo_contenido = "";
            if (ImageIO.read(uploadedFile) == null){
                //IS NOT A PHOTO
                tipo_contenido = "VIDEO";
                uploadResult = cloudinary.uploader().uploadLarge(uploadedFile, ObjectUtils.emptyMap());
            } else{
                //PHOTO
                uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
                tipo_contenido = "FOTO";

            }

            Multimedia multimedia = Multimedia.builder()
                    .tipoContenido(tipo_contenido)
                    .urlContenido(uploadResult.get("url").toString())
                    .extension(StringUtils.getFilenameExtension(photoOrVideo.getOriginalFilename()))
                    .paso(paso)
                    .build();
            multimediaRepository.save(multimedia);

            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
