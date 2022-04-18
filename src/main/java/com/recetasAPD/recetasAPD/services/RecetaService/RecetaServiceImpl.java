package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.ItemIngredienteRequest;
import com.recetasAPD.recetasAPD.dtos.ItemIngredienteResponse;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.exceptions.*;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import com.recetasAPD.recetasAPD.services.FotoService.FotoService;
import com.recetasAPD.recetasAPD.services.IngredienteService.IngredienteService;
import com.recetasAPD.recetasAPD.services.ItemIngredienteService.ItemIngredienteService;
import com.recetasAPD.recetasAPD.services.TipoService.TipoService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Autowired
    private ItemIngredienteService itemIngredienteService;

    @Autowired
    private FotoService fotoService;

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
    //@Transactional(rollbackOn = {UserNotFoundException.class, TipoNotFoundException.class, IngredienteNotFoundException.class})
    public Receta addReceta(RecetaRequest r,List<MultipartFile> fotos) {
        return this.convertRecetaRequestToReceta(r,fotos);
    }

    private Receta convertRecetaRequestToReceta(RecetaRequest recetaRequest, List<MultipartFile> fotos){
        //Me falta hacerlo transactional
         Receta receta = Receta.builder()
                .titulo(recetaRequest.getNombre())
                .descripcion(recetaRequest.getDescripcion())
                .porciones(recetaRequest.getPorciones())
                .cantidadPersonas(recetaRequest.getCantPersonas())
                .estado(0)
                .usuario(usuarioService.findById(recetaRequest.getIdUsuario()))
                .tipo(tipoService.findById(recetaRequest.getTipo()))
                .pasos(entityDtoConverter.convertPasoRequestToPaso(recetaRequest.getPasos()))
                .fecha(LocalDateTime.now())
                .build();
        recetaRepository.save(receta);//Para generar el id

        //Items ingredientes
        List<ItemIngrediente> items = this.convertAndSaveItemIngredienteRequestToItemIngrediente(recetaRequest.getItemIngredientes(), receta);
        if (items != null){
            receta.setIngredientes(items);
            this.update(receta);

            //Fotos de la receta (NO LOS PASOS)
            receta.setGaleria(this.convertAndSaveFotoImageFileToFoto(fotos,receta));
            this.update(receta);


            return receta;
        } else {
            throw new RecetaNotCreatedException("Algun ingrediente no existe");
        }

    }

    private List<ItemIngrediente> convertAndSaveItemIngredienteRequestToItemIngrediente(List<ItemIngredienteRequest> items, Receta receta){
        List<ItemIngrediente> itemIngredientes = new ArrayList<>();
        ItemIngrediente itemIngrediente;
        Ingrediente i;

        try {
            for (ItemIngredienteRequest item : items) {
                i = ingredienteService.findById(item.getIdIngrediente());
                itemIngrediente = entityDtoConverter.convertItemIngredienteRequestToItemIngrediente(item);
                itemIngrediente.setIngrediente(i);
                itemIngredientes.add(itemIngrediente);
                itemIngrediente.setReceta(receta);
                itemIngredienteService.save(itemIngrediente);
            }
            return itemIngredientes;
        } catch (IngredienteNotFoundException e){
            recetaRepository.delete(receta);
            itemIngredientes = null;
            return itemIngredientes;
        }

    }

    private List<Foto> convertAndSaveFotoImageFileToFoto(List<MultipartFile> fotos, Receta receta){
        List<Foto> fotosGuardadas = new ArrayList<>();
        for (MultipartFile foto : fotos){
            String url = fotoService.uploadPhoto(foto); //Subimos la foto a Cloudinary y obtenemos el link
            Foto f = fotoService.savePhotoByUrlAndTitle(url);
            f.setReceta(receta);
            fotoService.update(f);
            fotosGuardadas.add(f);
        }
        return fotosGuardadas;
    }


}
