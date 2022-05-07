package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.UtilizadoRequest;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.exceptions.*;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import com.recetasAPD.recetasAPD.services.FotoService.FotoService;
import com.recetasAPD.recetasAPD.services.IngredienteService.IngredienteService;
import com.recetasAPD.recetasAPD.services.UtilizadoService.UtilizadoService;
import com.recetasAPD.recetasAPD.services.MultimediaService.MultimediaService;
import com.recetasAPD.recetasAPD.services.PasoService.PasoService;
import com.recetasAPD.recetasAPD.services.TipoService.TipoService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private UtilizadoService utilizadoService;

    @Autowired
    private FotoService fotoService;

    @Autowired
    private MultimediaService multimediaService;

    @Autowired
    private PasoService pasoService;


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
            throw new RecetasEmptyException("No se encontraron recetas");
        }
        return recetaRepository.findAll();
    }

    @Override
    public List<Receta> findByTitulo(String nombre, Integer orden) {
        if(!recetaRepository.findByNombre(nombre).isEmpty()){
            if(orden == 1){
                return recetaRepository.findByNombreOrderByFechaAsc(nombre);
            }else {
                return recetaRepository.findByNombre(nombre);
            }
        }else{
            throw new RecetasEmptyException("El nombre ingresado no corresponde a una receta");
        }
    }

    @Override
    public List<Receta> getRecetaWithoutIngrediente(Integer idIngrediente, Integer orden) {
        //falta la excepcion tambien
        List<Receta> recetas;
        if(orden == 0){
            recetas = recetaRepository.getAllRecetaWithoutIngredientTitulo(ingredienteService.findById(idIngrediente));
        }else {
            recetas = recetaRepository.getAllRecetaWithoutIngredientAntiguedad(ingredienteService.findById(idIngrediente));
        }
        if(recetas.isEmpty()){
            throw new RecetasEmptyException("No existen recetas que no contengan ese ingrediente");
        }
        return recetas;

    }

    @Override
    public List<Receta> getRecetaWithIngrediente(Integer idIngrediente, Integer orden) {
            //falta la excepcion tambien
            List<Receta> recetas;
            if(orden == 0){
                recetas = recetaRepository.getAllRecetaWithIngredientTitulo(ingredienteService.findById(idIngrediente));
            }else {
                recetas = recetaRepository.getAllRecetaWithIngredientAntiguedad(ingredienteService.findById(idIngrediente));
            }
            if(recetas.isEmpty()){
                throw new RecetasEmptyException("No existen recetas con ese ingrediente");
            }
            return recetas;
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
    public Receta existeRecetaByNombreAndTitulo(String nombre, Integer idUsuario) {
        Usuario u = usuarioService.findById(idUsuario);
        Receta r = recetaRepository.findByNombreAndUsuario(nombre,u);
        if (r == null){
            //No existe la receta
            Receta nuevaReceta = Receta.builder()
                    .nombre(nombre)
                    .usuario(u)
                    //.fecha(LocalDateTime.now())
                    //.estado(0)
                    .build();
            return recetaRepository.save(nuevaReceta);
        }
        throw new RecetaAlreadyCreatedException("Ya tiene un receta con este nombre, desea editar o reemplazar?");
    }

    @Override
    public Receta crearRecetaByNombreAndTitulo(String nombre, Integer idUsuario) {
        Receta nuevaReceta = Receta.builder()
                .nombre(nombre)
                .usuario(usuarioService.findById(idUsuario))
                .recetaExt(RecetaExt.builder()
                        .fecha(LocalDateTime.now())
                        .estado(0)
                        //.receta()
                        .build())
                .build();
        recetaRepository.save(nuevaReceta);

        return nuevaReceta;
    }

    @Override
    public Receta updateRecetaFieldsOnCreation(RecetaRequest recetaDTO, List<MultipartFile> fotos) {
        Receta r = this.findById(recetaDTO.getIdReceta());
        r.setPorciones(recetaDTO.getPorciones());
        r.setCantidadPersonas(recetaDTO.getCantidadPersonas());
        r.setDescripcion(recetaDTO.getDescripcion());
        r.setFoto(this.convertAndSaveFotoImageFileToFoto(fotos,r));
        r.setTipo(tipoService.findById(recetaDTO.getTipo()));
        r.setUsuario(usuarioService.findById(recetaDTO.getIdUsuario()));
        this.update(r);
        return r;
    }

    @Override
    public Receta findById(Integer id) {
        Optional<Receta> r = recetaRepository.findById(id);
        if (r.isPresent()){
            return r.get();
        }
        throw new RecetaNotCreatedException("No existe una receta con ID "+ id);
    }

    @Override
    public List<Receta> findRecetaByTipo(Integer idTipo, Integer orden) {
        Tipo tipo = tipoService.findById(idTipo);
        if (!recetaRepository.findByTipoOrderByNombre(tipo).isEmpty()) {
            if (orden == 1) {
                return recetaRepository.findByTipoOrderByNombre(tipo);
            } else {
                return recetaRepository.findByTipoOrderByFecha(tipo);
            }
        }else{
            throw new RecetasEmptyException("No se encontro una receta asociada a este tipo");
        }
    }




    private List<Utilizado> convertAndSaveItemIngredienteRequestToItemIngrediente(List<UtilizadoRequest> items, Receta receta){
        List<Utilizado> utilizados = new ArrayList<>();
        Utilizado utilizado;
        Ingrediente i;

        try {
            for (UtilizadoRequest item : items) {
                i = ingredienteService.findById(item.getIdIngrediente());
                utilizado = entityDtoConverter.convertItemIngredienteRequestToItemIngrediente(item);
                utilizado.setIngrediente(i);
                utilizados.add(utilizado);
                utilizado.setReceta(receta);
                utilizadoService.save(utilizado);
            }
            return utilizados;
        } catch (IngredienteNotFoundException e){
            recetaRepository.delete(receta);
            utilizados = null;
            return utilizados;
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


    @Override
    public Receta generarRecetaConDistintasPorciones(RecetaRequest receta, Integer cantidadPorciones) {
        return null;
    }

    @Override
    public Receta generarRecetaConDistintasCantidades(Receta receta, String multiplo,Usuario usuario) {
        float variable;

        Receta recetaAux = this.findById(receta.getIdReceta());
        LocalDateTime Fecha = LocalDateTime.now();
        List<Utilizado> ingredientesViejos = recetaAux.getIngredientes();
        List<Utilizado> ingredientesNuevos = new ArrayList<>();


        if (multiplo.equalsIgnoreCase("Doble")){
            variable = 2;

        }else{
            variable = 0.5F;
        }
        recetaAux.setUsuario(usuario);
        recetaAux.setIdReceta(null);
        recetaAux.setPorciones((int) (recetaAux.getPorciones()*variable));
        recetaAux.setCantidadPersonas((int) (recetaAux.getCantidadPersonas()*variable));
        recetaRepository.save(recetaAux);
        for (Utilizado i: ingredientesViejos){
            Utilizado nuevoUtilizado = new Utilizado(i.getIngrediente(),i.getCantidad()*variable,i.getObservaciones(),recetaAux,i.getUnidad());
            utilizadoService.save(nuevoUtilizado);
            ingredientesNuevos.add(nuevoUtilizado);


        }
        recetaAux.setIngredientes(ingredientesNuevos);
        recetaRepository.save(recetaAux);


        return recetaAux;
    }

    @Override
    public Receta generarRecetaConDistintaCantidadIngrediente(RecetaRequest receta, String ingrediente, Integer cantidad) {
        return null;
    }


}
