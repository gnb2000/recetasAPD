package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.exceptions.RecetasEmptyException;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import com.recetasAPD.recetasAPD.services.IngredienteService.IngredienteService;
import com.recetasAPD.recetasAPD.services.TipoService.TipoService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecetaServiceImpl implements RecetaService{
    @Autowired // Sirve para inyectar lo del repositorio y para que funcione como singleton.
    private RecetaRepository recetaRepository;

    @Autowired
    private TipoService tipoService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @Override
    public void save(Receta receta) {
        recetaRepository.save(receta);
    }

    @Override
    public void update(Receta receta) {
        recetaRepository.save(receta);
    }

    @Override
    public void delete(Receta receta) {
        recetaRepository.delete(receta);
    }

    @Override
    public List<Receta> getAll()  {
        if(recetaRepository.findAll().isEmpty()){
            throw new RecetasEmptyException("No tenemos recetas cargadas aun!");
        }
        return recetaRepository.findAll();
    }

    @Override
    public List<Receta> findByTitulo(String nombre, Integer orden) {
        if(!recetaRepository.findByTitulo(nombre).isEmpty()){
            if(orden == 1){
                return recetaRepository.findByTituloOrderByFechaAsc(nombre);
            }else {
                return recetaRepository.findByTitulo(nombre);
            }
        }else{
            throw new RecetasEmptyException("¡No hay recetas con ese nombre!");
        }
    }

    @Override
    public Receta getLast() {
        if(!recetaRepository.findAll().isEmpty()){
            return recetaRepository.findTop1ByOrderByFechaDesc();
        }else{
            throw new RecetasEmptyException("¡No hay recetas cargadas!");
        }
    }

    @Override
    public Receta addReceta(RecetaRequest r) {
        return this.convertRecetaRequestToReceta(r);
    }

    private Receta convertRecetaRequestToReceta(RecetaRequest recetaRequest){
        Receta receta = Receta.builder()
                .titulo(recetaRequest.getNombre())
                .descripcion(recetaRequest.getDescripcion())
                .porciones(recetaRequest.getPorciones())
                .cantidadPersonas(recetaRequest.getCantPersonas())
                .estado(0)
                .fecha(LocalDateTime.now())
                .build();

        Usuario u = usuarioService.findById(recetaRequest.getIdUsuario());
        receta.setUsuario(u);

        Tipo tipo = tipoService.findById(recetaRequest.getTipo());
        receta.setTipo(tipo);

        List<ItemIngrediente> itemIngredientes = entityDtoConverter.convertItemIngredienteRequestListToItemIngredienteList(recetaRequest.getItemIngredientes());
        receta.setIngredientes(itemIngredientes);



        return recetaRepository.save(receta);

    }


}
