package com.recetasAPD.recetasAPD.services.FavoritosService;

import com.recetasAPD.recetasAPD.entities.Favorita;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Usuario;
import com.recetasAPD.recetasAPD.repositories.FavoritosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritosServiceImp implements FavoritosService{
    @Autowired
    private FavoritosRepository favoritosRepository;

    @Override
    public void save(Favorita favorita) {
        favoritosRepository.save(favorita);
    }

    @Override
    public void update(Favorita favorita) {
        favoritosRepository.save(favorita);
    }

    @Override
    public void delete(Favorita favorita) {
        favoritosRepository.delete(favorita);
    }

    @Override
    public void addFavorita(Usuario usuario, Receta receta) {
            Favorita favorita = new Favorita();
            favorita.setUsuario(usuario);
            favorita.setReceta(receta);
            favorita.setEstado(true);
            favoritosRepository.save(favorita);
    }

    @Override
    public List<Favorita> getFavoritaByIdUsuario(Integer idUsuario) {
        return favoritosRepository.findAllByIdUsuario(idUsuario);
    }

}
