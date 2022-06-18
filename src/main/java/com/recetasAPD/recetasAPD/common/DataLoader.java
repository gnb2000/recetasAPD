package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.repositories.*;
import com.recetasAPD.recetasAPD.services.CalificacionService.CalificacionService;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private CalificacionRepository calificacionRepository;


    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private PasoRepository pasoRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Autowired
    private RecetaService recetaService;

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ItemIngredienteRepository itemIngredienteRepository;

    @Autowired
    private RecetaExtRepository recetaExtRepository;

    @Autowired
    private UsuarioExtRepository usuarioExtRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
       crearRecetas();
        crearFotos();
        crearTipo();
        crearPasos();
        crearMultimedia();
        crearIngredientes();
        crearUnidad();
        crearItemIngrediente();
        crearUsuario();
        completarReceta();
        generarReceta();
       // generarCalificaciones();

        }


    private void crearRecetas() {
        Receta receta = Receta.builder()
                .nombre("Receta1")
                .porciones(4)
                .cantidadPersonas(8)
                .descripcion("La milanesa perfecta para comer en familia")
                .build();
        recetaService.save(receta);

        RecetaExt rExt = RecetaExt.builder()
                        .estado(2)
                        .fecha(LocalDateTime.now())
                        .receta(receta)
                        .build();
        recetaExtRepository.save(rExt);

        Receta receta2 = Receta.builder()
                .nombre("Receta2")
                .porciones(4)
                .cantidadPersonas(8)
                .descripcion("La milanesa perfecta para comer en familia")
                .build();
        recetaService.save(receta2);

        RecetaExt rExt2 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta2)
                .build();
        recetaExtRepository.save(rExt2);

        Receta receta3 = Receta.builder()
                .nombre("Receta3")
                .porciones(4)
                .cantidadPersonas(8)
                .descripcion("La milanesa perfecta para comer en familia")
                .build();
        recetaService.save(receta3);

        RecetaExt rExt3 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta3)
                .build();
        recetaExtRepository.save(rExt3);

    }

    private void crearFotos() {
        Foto foto = Foto.builder()
                .urlFoto("https://d1uz88p17r663j.cloudfront.net/original/03375ce3c9006f7693221eaa2f803b34__0001_SupremaPure.jpg")
                .receta(recetaService.findById(1))
                .build();
        fotoRepository.save(foto);

    }

    private void crearTipo() {
        Tipo tipo = Tipo.builder()
                .descripcion("ENSALADAS")
                .build();
        tipoRepository.save(tipo);
    }

    private void crearPasos(){
        Paso paso = Paso.builder()
                .texto("Preparar bowl con pan rallado")
                .nroPaso(1)
                .receta(recetaService.findById(1))
                .build();
        pasoRepository.save(paso);

    }

    private void crearMultimedia(){
        Multimedia multimedia = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("https://upload.wikimedia.org/wikipedia/commons/c/c3/Semmelmehl.jpg")
                .build();
        multimediaRepository.save(multimedia);

        Multimedia multimedia2 = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("https://cookpad.com/ar/recetas/14029497-pan-rallado-sin-harina-keto")
                .build();
        multimediaRepository.save(multimedia);
    }
    private void crearIngredientes(){
        Ingrediente ingrediente = Ingrediente.builder()
                .nombre("Nuez Moscada")
                .build();
        ingredienteRepository.save(ingrediente);

    }
    private void crearUnidad(){
        Unidad unidad = Unidad.builder()
                .descripcion("KG")
                .build();
        unidadRepository.save(unidad);
    }
    private void crearItemIngrediente(){
        Utilizado utilizado = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(1))
                .cantidad(250)
                .receta(recetaService.findById(1))
                .observaciones("Uso restringido")
                .unidad(unidadRepository.getById(1))
                .build();
        itemIngredienteRepository.save(utilizado);
    }
    private void crearUsuario(){
        Usuario usuario = Usuario.builder()
                .tipoUsuario(1)
                .avatar("https://www.uniformall.es/assets/blog/792388003-cocinero-jefe-cocina-de-restaurante-cenar-presentar.jpg")
                .mail("iviglia.j@hotmail.com")
                .nombre("JuanJose")
                //.password("123456")
                .nickname("juaniviglia")
                .build();
        usuarioRepository.save(usuario);

        UsuarioExt usuarioExt = UsuarioExt.builder()
                .password("123456")
                .usuario(usuario)
                .build();
        usuarioExtRepository.save(usuarioExt);

        Usuario usuario2 = Usuario.builder()
                .tipoUsuario(1)
                .avatar("https://www.uniformall.es/assets/blog/792388003-cocinero-jefe-cocina-de-restaurante-cenar-presentar.jpg")
                .mail("2")
                .nombre("2")
                //.password("123456")
                .nickname("2")
                .build();
        usuarioRepository.save(usuario2);

        UsuarioExt usuarioExt2 = UsuarioExt.builder()
                .password("123456")
                .usuario(usuario2)
                .build();
        usuarioExtRepository.save(usuarioExt2);


    }
    private void completarReceta(){
        Receta r = recetaService.findById(1);
        r.setTipo(tipoRepository.getById(1));
        r.setUsuario(usuarioRepository.getById(1));
        recetaService.save(r);

        Receta r2 = recetaService.findById(2);
        r.setTipo(tipoRepository.getById(1));
        r.setUsuario(usuarioRepository.getById(1));
        recetaService.save(r2);

        Receta r3 = recetaService.findById(3);
        r.setTipo(tipoRepository.getById(1));
        r.setUsuario(usuarioRepository.getById(1));
        recetaService.save(r3);
    }
    private void generarReceta() {

        recetaService.generarRecetaConDistintasCantidades(1,"Doble",1);
       // recetaService.generarRecetaConDistintasPorciones(1,8,1);
        //recetaService.generarRecetaConDistintaCantidadIngrediente(1,1,1,500,1);

    }

    private void generarCalificaciones(){

        calificacionService.crearCalificacion(1,1,3.0F,"GRAN RECETA PA");
        calificacionService.crearCalificacion(2,1,4.0F,"GRAN RECETA PA");
        calificacionService.crearCalificacion(3,1,1.0F,"hola");
        calificacionService.crearCalificacion(4,1,5.0F,"hola");
    }



}

