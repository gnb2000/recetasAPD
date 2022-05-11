package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.repositories.*;
import com.recetasAPD.recetasAPD.services.RecetaService.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataLoader implements ApplicationRunner {
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
        //crearMultimedia();
        crearIngredientes();
        crearUnidad();
        crearItemIngrediente();
        crearUsuario();
        completarReceta();


        generarReceta();

        }
    private void crearRecetas() {
        Receta receta = Receta.builder()
                .nombre("RecetaPrueba1")
                .porciones(4)
                .cantidadPersonas(8)
                .descripcion("Prueba")
                .build();
        recetaService.save(receta);

        RecetaExt rExt = RecetaExt.builder()
                        .estado(3)
                        .fecha(LocalDateTime.now())
                        .receta(receta)
                        .build();
        recetaExtRepository.save(rExt);
    }

    private void crearFotos() {
        Foto foto = Foto.builder()
                .urlFoto("probandoFoto1")
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
                .texto("Es el primero paso de la reseta")
                .nroPaso(1)
                .texto("Esto es una prueba")
                .receta(recetaService.findById(1))
                .build();
        pasoRepository.save(paso);

    }

    private void crearMultimedia(){
        Multimedia multimedia = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("estoesunaprueba")
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
                .descripcion("Es la unidad del gramo")
                .build();
        unidadRepository.save(unidad);
    }
    private void crearItemIngrediente(){
        Utilizado utilizado = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(1))
                .cantidad(250)
                .receta(recetaService.findById(1))
                .observaciones("estoesunaprueba")
                .unidad(unidadRepository.getById(1))
                .build();
        itemIngredienteRepository.save(utilizado);
    }
    private void crearUsuario(){
        Usuario usuario = Usuario.builder()
                .tipoUsuario(1)
                .avatar("estoesunaprueba")
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


    }
    private void completarReceta(){
        Receta r = recetaService.findById(1);
        r.setTipo(tipoRepository.getById(1));
        r.setUsuario(usuarioRepository.getById(1));
        recetaService.save(r);
    }
    private void generarReceta() {

        recetaService.generarRecetaConDistintasCantidades(1,"Doble",1);
       // recetaService.generarRecetaConDistintasPorciones(1,8,1);
        //recetaService.generarRecetaConDistintaCantidadIngrediente(1,1,1,500,1);


    }



}

