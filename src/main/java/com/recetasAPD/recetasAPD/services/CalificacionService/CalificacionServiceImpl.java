package com.recetasAPD.recetasAPD.services.CalificacionService;


import com.recetasAPD.recetasAPD.entities.Calificacion;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.repositories.CalificacionRepository;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceImpl implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RecetaService recetaService;

    @Override
    public void save(Calificacion calificacion){calificacionRepository.save(calificacion);}

    @Override
    public void delete(Calificacion calificacion) {calificacionRepository.delete(calificacion);}

    @Override
    public void update(Calificacion calificacion) {calificacionRepository.save(calificacion);}

    @Override
    public Calificacion crearCalificacion(int idReceta, int idUsuario, Integer puntuacion,String comentario) {
        boolean bandera = existeCalificacionUsuario(idUsuario);
        Calificacion nuevaCalificacion;
        if (bandera == false){
                    nuevaCalificacion = Calificacion.builder()
                    .usuario(usuarioService.findById(idUsuario))
                    .calificacion(puntuacion)
                    .receta(recetaService.findById(idReceta))
                    .comentarios(comentario)
                    .build();
            calificacionRepository.save(nuevaCalificacion);
        }else {
            nuevaCalificacion = calificacionRepository.findByUsuario(usuarioService.findById(idUsuario));
            nuevaCalificacion.setCalificacion(puntuacion);
            calificacionRepository.save(nuevaCalificacion);
        }
        Receta r = recetaService.findById(idReceta);
        r.setCalificaciones(calificacionRepository.findByReceta(recetaService.findById(idReceta)));


        return nuevaCalificacion;
    }

    @Override
    public List<Calificacion> obtenerCalificacionesPorReceta(Integer idReceta) {
        return calificacionRepository.findByReceta(recetaService.findById(idReceta));
    }

    @Override
    public boolean existeCalificacionUsuario(Integer idUsuario) {
       Calificacion calificacion = calificacionRepository.findByUsuario(usuarioService.findById(idUsuario));
        if (calificacion == null){
            return false;
        }else{
            return true;
        }
    }


}
