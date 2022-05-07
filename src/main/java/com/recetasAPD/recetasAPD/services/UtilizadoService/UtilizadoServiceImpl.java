package com.recetasAPD.recetasAPD.services.UtilizadoService;

import com.recetasAPD.recetasAPD.dtos.UtilizadoRequest;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.entities.Utilizado;
import com.recetasAPD.recetasAPD.entities.Receta;
import com.recetasAPD.recetasAPD.entities.Unidad;
import com.recetasAPD.recetasAPD.repositories.ItemIngredienteRepository;
import com.recetasAPD.recetasAPD.services.IngredienteService.IngredienteService;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import com.recetasAPD.recetasAPD.services.UnidadService.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizadoServiceImpl implements UtilizadoService {

    @Autowired
    private ItemIngredienteRepository itemIngredienteRepository;

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private UnidadService unidadService;

    @Override
    public void save(Utilizado utilizado) {
        itemIngredienteRepository.save(utilizado);
    }

    @Override
    public void update(Utilizado utilizado) {
        itemIngredienteRepository.save(utilizado);
    }

    @Override
    public void delete(Utilizado utilizado) {
        itemIngredienteRepository.delete(utilizado);
    }

    @Override
    public Utilizado addIngredienteToReceta(UtilizadoRequest utilizadoRequest) {
        Receta r = recetaService.findById(utilizadoRequest.getIdReceta());
        Ingrediente ingrediente = ingredienteService.findById(utilizadoRequest.getIdIngrediente());
        Unidad u = unidadService.findById(utilizadoRequest.getIdUnidad());
        Utilizado item = Utilizado.builder()
                .ingrediente(ingrediente)
                .receta(r)
                .cantidad(utilizadoRequest.getCantidad())
                .unidad(u)
                .observaciones(utilizadoRequest.getObservaciones())
                .build();
        itemIngredienteRepository.save(item);
        r.getIngredientes().add(item);
        recetaService.update(r);
        return item;
    }

    @Override
    public List<Utilizado> getItemIngredientesByReceta(Integer idReceta) {
        Receta r = recetaService.findById(idReceta);
        return itemIngredienteRepository.findByReceta(r);
    }
}
