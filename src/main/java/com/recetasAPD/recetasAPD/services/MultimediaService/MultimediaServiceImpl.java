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
    public Multimedia uploadAndSaveFile(MultipartFile photoOrVideo, Paso paso) {
        try {
            File uploadedFile = convertMultiPartToFile(photoOrVideo);
            Map uploadResult;
            String tipo_contenido = "";
            if (ImageIO.read(uploadedFile) == null){
                //IS NOT A PHOTO
                tipo_contenido = "video";
                uploadResult = cloudinary.uploader().uploadLarge(uploadedFile, ObjectUtils.asMap("resource_type", "video"));
            } else{
                //PHOTO
                uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
                tipo_contenido = "foto";
            }
            System.out.println(tipo_contenido+" subida a cloudinary con exito");

            Multimedia multimedia = Multimedia.builder()
                    .tipo_contenido(tipo_contenido)
                    .urlContenido(uploadResult.get("url").toString())
                    .extension(StringUtils.getFilenameExtension(photoOrVideo.getOriginalFilename()))
                    .paso(paso)
                    .build();


            return  multimediaRepository.save(multimedia);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadVideo(MultipartFile video) {
        try {
            File uploadedFile = convertMultiPartToFile(video);
            Map uploadResult = cloudinary.uploader().uploadLarge(uploadedFile, ObjectUtils.asMap("resource_type", "video"));
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

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
