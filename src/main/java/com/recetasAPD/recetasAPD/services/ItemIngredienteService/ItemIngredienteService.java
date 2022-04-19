package com.recetasAPD.recetasAPD.services.ItemIngredienteService;

import com.recetasAPD.recetasAPD.dtos.ItemIngredienteRequest;
import com.recetasAPD.recetasAPD.entities.Ingrediente;
import com.recetasAPD.recetasAPD.entities.ItemIngrediente;

import java.util.List;

public interface ItemIngredienteService {

    void save (ItemIngrediente itemIngrediente);
    void update(ItemIngrediente itemIngrediente);
    void delete(ItemIngrediente itemIngrediente);
    ItemIngrediente addIngredienteToReceta(ItemIngredienteRequest itemIngredienteRequest);
    List<ItemIngrediente> getItemIngredientesByReceta(Integer idReceta);
}
