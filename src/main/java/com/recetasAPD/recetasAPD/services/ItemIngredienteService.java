package com.recetasAPD.recetasAPD.services;

import com.recetasAPD.recetasAPD.entities.ItemIngrediente;

public interface ItemIngredienteService {
    void save (ItemIngrediente itemIngrediente);
    void update(ItemIngrediente itemIngrediente);
    void delete(ItemIngrediente itemIngrediente);
}
