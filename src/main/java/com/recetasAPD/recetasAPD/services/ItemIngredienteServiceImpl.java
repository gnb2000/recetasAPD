package com.recetasAPD.recetasAPD.services;

import com.recetasAPD.recetasAPD.entities.ItemIngrediente;
import com.recetasAPD.recetasAPD.repositories.ItemIngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemIngredienteServiceImpl implements ItemIngredienteService{
    @Autowired
    private ItemIngredienteRepository itemIngredienteRepository;

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
}
