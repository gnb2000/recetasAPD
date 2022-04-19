package com.recetasAPD.recetasAPD.services.ItemIngredienteService;

import com.recetasAPD.recetasAPD.dtos.ItemIngredienteRequest;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.entities.ItemIngrediente;
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
public class ItemIngredienteServiceImpl implements ItemIngredienteService{

    @Autowired
    private ItemIngredienteRepository itemIngredienteRepository;

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private UnidadService unidadService;

    @Override
    public void save(ItemIngrediente itemIngrediente) {
        itemIngredienteRepository.save(itemIngrediente);
    }

    @Override
    public void update(ItemIngrediente itemIngrediente) {
        itemIngredienteRepository.save(itemIngrediente);
    }

    @Override
    public void delete(ItemIngrediente itemIngrediente) {
        itemIngredienteRepository.delete(itemIngrediente);
    }

    @Override
    public ItemIngrediente addIngredienteToReceta(ItemIngredienteRequest itemIngredienteRequest) {
        Receta r = recetaService.findById(itemIngredienteRequest.getIdReceta());
        Ingrediente ingrediente = ingredienteService.findById(itemIngredienteRequest.getIdIngrediente());
        Unidad u = unidadService.findById(itemIngredienteRequest.getIdUnidad());
        ItemIngrediente item = ItemIngrediente.builder()
                .ingrediente(ingrediente)
                .receta(r)
                .cantidad(itemIngredienteRequest.getCantidad())
                .unidad(u)
                .observaciones(itemIngredienteRequest.getObservaciones())
                .build();
        itemIngredienteRepository.save(item);
        r.getIngredientes().add(item);
        recetaService.update(r);
        return item;
    }

    @Override
    public List<ItemIngrediente> getItemIngredientesByReceta(Integer idReceta) {
        Receta r = recetaService.findById(idReceta);
        return itemIngredienteRepository.findByReceta(r);
    }
}
