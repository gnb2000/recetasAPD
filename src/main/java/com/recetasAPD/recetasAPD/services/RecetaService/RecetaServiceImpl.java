package com.recetasAPD.recetasAPD.services.RecetaService;

import com.recetasAPD.recetasAPD.common.EntityDtoConverter;
import com.recetasAPD.recetasAPD.dtos.UtilizadoRequest;
import com.recetasAPD.recetasAPD.dtos.RecetaRequest;
import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.exceptions.*;
import com.recetasAPD.recetasAPD.repositories.RecetaExtRepository;
import com.recetasAPD.recetasAPD.repositories.RecetaRepository;
import com.recetasAPD.recetasAPD.services.CalificacionService.CalificacionService;
import com.recetasAPD.recetasAPD.services.FotoService.FotoService;
import com.recetasAPD.recetasAPD.services.IngredienteService.IngredienteService;
import com.recetasAPD.recetasAPD.services.RecetaExtService.RecetaExtService;
import com.recetasAPD.recetasAPD.services.UtilizadoService.UtilizadoService;
import com.recetasAPD.recetasAPD.services.MultimediaService.MultimediaService;
import com.recetasAPD.recetasAPD.services.PasoService.PasoService;
import com.recetasAPD.recetasAPD.services.TipoService.TipoService;
import com.recetasAPD.recetasAPD.services.UsuarioService.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.*;

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

    @Autowired
    private RecetaExtRepository recetaExtrepository;

    @Autowired
    private CalificacionService calificacionService;

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
        List<Receta> ayuda = recetaRepository.findByNombre(nombre);
        if(ayuda.size()!=0){
            if(orden == 1){
                return recetaRepository.findByNombreOrderByFechaAsc(nombre);
            }else {
                System.out.println("entro al find");
                return recetaRepository.findByNombre(nombre);
            }
        }else{
            throw new RecetasEmptyException("El nombre ingresado no corresponde a una receta");
        }
    }

    @Override
    public List<Receta> getRecetaWithoutIngrediente(String ingrediente, Integer orden) {
        List<Receta> recetas;
        if(orden == 0){
            recetas = recetaRepository.getAllRecetaWithoutIngredientTitulo(ingrediente);
        }else {
            recetas = recetaRepository.getAllRecetaWithoutIngredientAntiguedad(ingrediente);
        }
        if(recetas.size()==0){
            throw new RecetasEmptyException("No existen recetas que no contengan ese ingrediente");
        }
        return recetas;

    }

    @Override
    public List<Receta> getRecetaWithIngrediente(String ingrediente, Integer orden) {
            List<Receta> recetas;
            if(orden == 0){
                recetas = recetaRepository.getAllRecetaWithIngredientTitulo(ingrediente);
            }else {
                recetas = recetaRepository.getAllRecetaWithIngredientAntiguedad(ingrediente);
            }
            if(recetas.size()==0){
                throw new RecetasEmptyException("No existen recetas con ese ingrediente");
            }
            return recetas;
        }

    @Override
    public List<Receta> obtenerMejoresRecetas(Integer cantidad) {
        List<Receta> recetas = recetaRepository.findAllRecetasWithoutProporciones();
        List<Receta> resultado = new ArrayList<>();
        float[][] matriz = new float[recetas.size()][2];
        int indice =0;
        for(Receta r: recetas){
            matriz[indice][0] = r.getIdReceta();
            matriz[indice][1] = CalcularPuntuacionReceta(r.getIdReceta());
            indice++;
        }
        Arrays.sort(matriz, Comparator.comparingDouble(a -> a[1]));
        indice = recetas.size()-1;
        if(recetas.size()>=cantidad) {
            while (cantidad > 0) {
                cantidad--;
                resultado.add(findById((int) matriz[indice][0]));
                indice--;
            }
        }
        if(recetas.isEmpty() || recetas.size()<cantidad){
            if (recetas.isEmpty()) {
                throw new RecetaNotCreatedException("No existen recetas disponibles");
            }
            else{
                throw new RecetasEmptyException("No existen "+cantidad+" recetas en la aplicación. Existen solo "+recetas.size()+".");
            }
        }
        return resultado;
    }

    @Override
    public Receta getLast() { // REVISAR ESTE
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
        throw new RecetaAlreadyCreatedException("Ya tiene un receta con este nombre, desea editar o reemplazar?", entityDtoConverter.convertRecetaToRecetaResponse(r));
    }

    @Override
    public Receta crearRecetaByNombreAndTitulo(String nombre, Integer idUsuario) {
        Receta nuevaReceta = Receta.builder()
                .nombre(nombre)
                .usuario(usuarioService.findById(idUsuario))
                .build();
        recetaRepository.save(nuevaReceta);
        RecetaExt nuevaRecetaExt = RecetaExt.builder()
                .fecha(LocalDateTime.now())
                .estado(0)
                .receta(nuevaReceta)
                .build();
        recetaExtrepository.save(nuevaRecetaExt);
        nuevaReceta.setRecetaExt(nuevaRecetaExt);
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
    public List<Receta> findRecetaByTipo(String tipo, Integer orden) {
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

    @Override
    public  List<Receta>  findRecetasUsuario(String user, Integer orden) {

        List<Receta> recetas;
        if (orden == 1) {
            recetas = recetaRepository.findByUsuarioTipoOrderByNombre(user);
        } else {
            recetas = recetaRepository.findByUsuarioTipoOrderByFecha(user);
        }
        if(recetas.size()==0){
            throw new RecetasEmptyException("No se encontro una receta asociada a este usuario");
        }
        return recetas;
    }

    @Override
    public Float CalcularPuntuacionReceta(Integer idReceta) {
        Float contador = 0.0F;
        Float suma = 0.0F;
        Float promedio = 0.0F;
        List<Calificacion> Calificaciones;
        Calificaciones = calificacionService.obtenerCalificacionesPorReceta(idReceta);
        for (Calificacion c: Calificaciones){
            contador++;
            suma = suma +  c.getCalificacion();
        }
        if (contador != 0) {
            promedio = suma / contador;
        }

        return promedio;
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
    public Receta generarRecetaConDistintasPorciones(Integer idReceta,Integer cantidadPorciones,Integer idUsuario) {
        Receta recetaAux = this.findById(idReceta);
        RecetaExt recetaExtAux = new RecetaExt();
        LocalDateTime Fecha = LocalDateTime.now();
        List<Utilizado> ingredientesViejos = recetaAux.getIngredientes();
        List<Utilizado> ingredientesNuevos = new ArrayList<>();
        Usuario usuario = usuarioService.findById(idUsuario);

        recetaAux.setUsuario(usuario);
        recetaAux.setIdReceta(null);
        recetaRepository.save(recetaAux);
        for (Utilizado i: ingredientesViejos){
            Utilizado nuevoUtilizado = new Utilizado(i.getIngrediente(),(cantidadPorciones*i.getCantidad())/recetaAux.getPorciones(),i.getObservaciones(),recetaAux,i.getUnidad());
            utilizadoService.save(nuevoUtilizado);
            ingredientesNuevos.add(nuevoUtilizado);
        }
        recetaExtAux.setFecha(Fecha);
        recetaExtAux.setEstado(3);
        recetaExtAux.setReceta(recetaAux);
        recetaAux.setIngredientes(ingredientesNuevos);
        recetaAux.setCantidadPersonas((cantidadPorciones*recetaAux.getCantidadPersonas())/recetaAux.getPorciones());
        recetaAux.setPorciones(cantidadPorciones);
        recetaExtrepository.save(recetaExtAux);
        recetaRepository.save(recetaAux);

        return recetaAux;
    }

    @Override
    public Receta generarRecetaConDistintasCantidades(Integer idReceta, String multiplo,Integer idUsuario) {
        float variable;
        Receta recetaAux = this.findById(idReceta);
        RecetaExt recetaExtAux = new RecetaExt();
        LocalDateTime Fecha = LocalDateTime.now();
        List<Utilizado> ingredientesViejos = recetaAux.getIngredientes();
        List<Utilizado> ingredientesNuevos = new ArrayList<>();
        Usuario usuario = usuarioService.findById(idUsuario);



        if (multiplo.equalsIgnoreCase("Doble")){
            variable = 2;

        }else{
            variable = 0.5F;
        }
        recetaAux.setUsuario(usuario);
        recetaAux.setIdReceta(null);
        recetaAux.setRecetaExt(null);
        recetaAux.setPorciones((int) (recetaAux.getPorciones()*variable));
        recetaAux.setCantidadPersonas((int) (recetaAux.getCantidadPersonas()*variable));
        recetaRepository.save(recetaAux);


        for (Utilizado i: ingredientesViejos){
            Utilizado nuevoItemIngrediente = new Utilizado(i.getIngrediente(),i.getCantidad()*variable,i.getObservaciones(),recetaAux,i.getUnidad());
            utilizadoService.save(nuevoItemIngrediente);
            ingredientesNuevos.add(nuevoItemIngrediente);
        }
        recetaAux.setIngredientes(ingredientesNuevos);
        //recetaExtrepository.save(recetaExtAux);


        recetaExtAux.setReceta(recetaAux);
        recetaExtAux.setEstado(3);
        recetaExtAux.setFecha(Fecha);
        recetaExtrepository.save(recetaExtAux);

        recetaAux.setRecetaExt(recetaExtAux);

        recetaRepository.save(recetaAux);
        return recetaAux;
    }

    @Override
    public Receta generarRecetaConDistintaCantidadIngrediente(Integer idReceta,Integer idUsuario,Integer idIngrediente,Integer cantidad,Integer idUnidad) {
        int nuevasPorciones = 0;
        Receta recetaAux = this.findById(idReceta);
        RecetaExt recetaExtAux = new RecetaExt();
        LocalDateTime Fecha = LocalDateTime.now();
        List<Utilizado> ingredientesViejos = recetaAux.getIngredientes();
        List<Utilizado> ingredientesNuevos = new ArrayList<>();
        Usuario usuario = usuarioService.findById(idUsuario);

        recetaAux.setUsuario(usuario);
        recetaAux.setIdReceta(null);
        recetaRepository.save(recetaAux);
        for(Utilizado i: ingredientesViejos){
            if (i.getIngrediente().getIdIngrediente() == idIngrediente ) {
               nuevasPorciones = (int) ((cantidad * recetaAux.getPorciones()) / i.getCantidad());
            }
        }
        for(Utilizado i: ingredientesViejos){
            Utilizado nuevoItemIngrediente = new Utilizado(i.getIngrediente(),(i.getCantidad()*nuevasPorciones)/recetaAux.getPorciones(),i.getObservaciones(),recetaAux,i.getUnidad());
            utilizadoService.save(nuevoItemIngrediente);
            ingredientesNuevos.add(nuevoItemIngrediente);

        }
        recetaExtAux.setReceta(recetaAux);
        recetaExtAux.setEstado(3);
        recetaExtAux.setFecha(Fecha);
        recetaExtrepository.save(recetaExtAux);
        recetaAux.setIngredientes(ingredientesNuevos);
        recetaAux.setCantidadPersonas((nuevasPorciones*recetaAux.getCantidadPersonas())/recetaAux.getCantidadPersonas());
        System.out.println(recetaAux.getCantidadPersonas());
        recetaAux.setPorciones(nuevasPorciones);
        recetaRepository.save(recetaAux);

        return recetaAux;
    }




}
