package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.entities.*;
import com.recetasAPD.recetasAPD.repositories.*;
import com.recetasAPD.recetasAPD.services.CalificacionService.CalificacionService;
import com.recetasAPD.recetasAPD.services.FavoritosService.FavoritosService;
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

    @Autowired
    private FavoritosService favoritaService;

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
        generarCalificaciones();
        agregarFavorita();

    }


    private void crearRecetas() {
        Receta receta = Receta.builder()
                .nombre("Rolls de Sushi")
                .porciones(12)
                .cantidadPersonas(2)
                .descripcion("Contrario a lo que se piensa, hacer sushi en casa no es tan complicado como parece. Si buscan una receta de sushi casero, les cuento que no es más que la receta del arroz, porque en verdad se puede rellenar con lo que ustedes deseen.")
                .build();
        recetaService.save(receta);

        RecetaExt rExt = RecetaExt.builder()
                        .estado(2)
                        .fecha(LocalDateTime.now())
                        .receta(receta)
                        .build();
        recetaExtRepository.save(rExt);

        Receta receta2 = Receta.builder()
                .nombre("Milanesa a la Napolitana")
                .porciones(2)
                .cantidadPersonas(2)
                .descripcion("La milanesa a la napolitana, aunque tiene nombre italiano, es un plato originario de la ciudad de Buenos Aires, Argentina, y consiste en carne, generalmente de vacuno, cubierta de salsa de tomate, jamón y queso. La carne se prepara de una manera muy sencilla, primero se reboza para que quede crujiente y, después, se cocina en el horno para que el queso se funda.")
                .build();
        recetaService.save(receta2);

        RecetaExt rExt2 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta2)
                .build();
        recetaExtRepository.save(rExt2);

        Receta receta3 = Receta.builder()
                .nombre("Pasta fresca")
                .porciones(3)
                .cantidadPersonas(3)
                .descripcion("Hacer pasta fresca en casa es sencillo. No requiere más que de un rodillo para estirarla y un cuchillo para cortarla. La puedes personalizar, tiene más sabor y mejor textura y produce una sensación de logro que no se te va a quitar en mucho tiempo.")
                .build();
        recetaService.save(receta3);

        RecetaExt rExt3 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta3)
                .build();
        recetaExtRepository.save(rExt3);


       /* Receta receta4 = Receta.builder()
                .nombre("Rolls de Sushi")
                .porciones(12)
                .cantidadPersonas(2)
                .descripcion("Contrario a lo que se piensa, hacer sushi en casa no es tan complicado como parece. Si buscan una receta de sushi casero, les cuento que no es más que la receta del arroz, porque en verdad se puede rellenar con lo que ustedes deseen.")
                .build();
        recetaService.save(receta4);

        RecetaExt rExt4 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta4)
                .build();
        recetaExtRepository.save(rExt4);

        Receta receta5 = Receta.builder()
                .nombre("Rolls de Sushi")
                .porciones(12)
                .cantidadPersonas(2)
                .descripcion("Contrario a lo que se piensa, hacer sushi en casa no es tan complicado como parece. Si buscan una receta de sushi casero, les cuento que no es más que la receta del arroz, porque en verdad se puede rellenar con lo que ustedes deseen.")
                .build();
        recetaService.save(receta5);

        RecetaExt rExt5 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta5)
                .build();
        recetaExtRepository.save(rExt5);

        Receta receta6 = Receta.builder()
                .nombre("Rolls de Sushi")
                .porciones(12)
                .cantidadPersonas(2)
                .descripcion("Contrario a lo que se piensa, hacer sushi en casa no es tan complicado como parece. Si buscan una receta de sushi casero, les cuento que no es más que la receta del arroz, porque en verdad se puede rellenar con lo que ustedes deseen.")
                .build();
        recetaService.save(receta6);

        RecetaExt rExt6 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta6)
                .build();
        recetaExtRepository.save(rExt6);


        Receta receta7 = Receta.builder()
                .nombre("Rolls de Sushi")
                .porciones(12)
                .cantidadPersonas(2)
                .descripcion("Contrario a lo que se piensa, hacer sushi en casa no es tan complicado como parece. Si buscan una receta de sushi casero, les cuento que no es más que la receta del arroz, porque en verdad se puede rellenar con lo que ustedes deseen.")
                .build();
        recetaService.save(receta7);

        RecetaExt rExt7 = RecetaExt.builder()
                .estado(2)
                .fecha(LocalDateTime.now())
                .receta(receta7)
                .build();
        recetaExtRepository.save(rExt7);*/




    }

    private void crearFotos() {
        Foto foto = Foto.builder()
                .urlFoto("http://res.cloudinary.com/recetasapd/image/upload/v1655997296/icgvuavet1bccirnceph.webp")
                .receta(recetaService.findById(1))
                .build();
        fotoRepository.save(foto);


        Foto foto2 = Foto.builder()
                .urlFoto("https://res.cloudinary.com/recetasapd/image/upload/v1656006326/p68nj8gjrkegiv44drfp.jpg")
                .receta(recetaService.findById(2))
                .build();
        fotoRepository.save(foto2);

        Foto foto3 = Foto.builder()
                .urlFoto("http://res.cloudinary.com/recetasapd/image/upload/v1656006246/ak5yvvyoodhzk9kp3kng.jpg")
                .receta(recetaService.findById(3))
                .build();
        fotoRepository.save(foto3);


    }

    private void crearTipo() {
        Tipo tipo = Tipo.builder()
                .descripcion("Sushi")
                .build();
        tipoRepository.save(tipo);

        Tipo tipo2 = Tipo.builder()
                .descripcion("Platos tradicionales")
                .build();
        tipoRepository.save(tipo2);

        Tipo tipo3 = Tipo.builder()
                .descripcion("Pastas")
                .build();
        tipoRepository.save(tipo3);
    }

    private void crearPasos(){
        Paso paso = Paso.builder()
                .texto("Lavar el arroz: Lavar el arroz en agua en  5-6 veces hasta que el agua salsa transparente o casi. Dejar reposar 30 minutos.")
                .nroPaso(1)
                .receta(recetaService.findById(1))
                .build();
        pasoRepository.save(paso);

        Paso paso2 = Paso.builder()
                .texto("Cocinar el arroz: Poner en una olla con igual cantidad de agua (2 tazas de arroz, 2 tazas de agua). Tapar. No vamos a sacar la tapa hasta el final de todo el proceso.")
                .nroPaso(2)
                .receta(recetaService.findById(1))
                .build();
        pasoRepository.save(paso2);

        Paso paso3 = Paso.builder()
                .texto("Enfriar el Arroz: Tras el reposo, colocar el arroz en un molde amplio (ideal si es de madera), que sea bien abierto. Volcar el aderezo sobre él e ir con cuidado separándolo con una cuchara y revolviéndolo.")
                .nroPaso(3)
                .receta(recetaService.findById(1))
                .build();
        pasoRepository.save(paso3);

        Paso paso4 = Paso.builder()
                .texto("Armado del sushi: Lo que hacemos es poner sobre una esterilla el alga nori. El alga siempre va con la parte brillante hacia abajo y las líneas que tiene en el mismo sentido que las líneas de la esterilla.")
                .nroPaso(4)
                .receta(recetaService.findById(1))
                .build();
        pasoRepository.save(paso4);

    }

    private void crearMultimedia(){

        // PASO 1
        Multimedia multimedia1 = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655995863/vjp9wfpmddxhynzwrxdp.jpg")
                .build();
        multimediaRepository.save(multimedia1);

        Multimedia multimedia12 = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".mp4")
                .tipo_contenido("video")
                .urlContenido("http://res.cloudinary.com/recetasapd/video/upload/v1656004557/p6ljen3whcnowbojssfk.mp4")
                .build();
        multimediaRepository.save(multimedia12);


        Multimedia multimedia2 = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655995912/iaajjsifhybec80qdj5b.jpg")
                .build();
        multimediaRepository.save(multimedia2);

        Multimedia multimedia3 = Multimedia.builder()
                .paso(pasoRepository.getById(1))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655995944/yaj9lhyfbb7ib2qqs7fj.jpg")
                .build();
        multimediaRepository.save(multimedia3);

        // PASO 2

        Multimedia multimedia4 = Multimedia.builder()
                .paso(pasoRepository.getById(2))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996056/enbmgyrtkoiam4ikandl.jpg")
                .build();
        multimediaRepository.save(multimedia4);

        Multimedia multimedia5 = Multimedia.builder()
                .paso(pasoRepository.getById(2))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996091/uytfip4d9llhsp7q5kb8.jpg")
                .build();
        multimediaRepository.save(multimedia5);

        Multimedia multimedia6 = Multimedia.builder()
                .paso(pasoRepository.getById(2))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996151/ptklepm3xlg7qla2bwws.jpg")
                .build();
        multimediaRepository.save(multimedia6);

        // PASO 3

        Multimedia multimedia7 = Multimedia.builder()
                .paso(pasoRepository.getById(3))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996258/zvovrwik1xu4orrpjb5n.jpg")
                .build();
        multimediaRepository.save(multimedia7);

        Multimedia multimedia8 = Multimedia.builder()
                .paso(pasoRepository.getById(3))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996272/jcd1pbvpulazy3g8nfjt.jpg")
                .build();
        multimediaRepository.save(multimedia8);

        // PASO 4

        Multimedia multimedia9 = Multimedia.builder()
                .paso(pasoRepository.getById(4))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996578/caly1a0dyza65hzrxgel.jpg")
                .build();
        multimediaRepository.save(multimedia9);

        Multimedia multimedia10 = Multimedia.builder()
                .paso(pasoRepository.getById(4))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996683/hv7d5adr6x9oiiqc3dxw.jpg")
                .build();
        multimediaRepository.save(multimedia10);

        Multimedia multimedia11 = Multimedia.builder()
                .paso(pasoRepository.getById(4))
                .extension(".jpg")
                .tipo_contenido("foto")
                .urlContenido("http://res.cloudinary.com/recetasapd/image/upload/v1655996718/nym0nwknhgl0gy8lbzow.jpg")
                .build();
        multimediaRepository.save(multimedia11);

    }
    private void crearIngredientes(){
        Ingrediente ingrediente1 = Ingrediente.builder()
                .nombre("Arroz")
                .build();
        ingredienteRepository.save(ingrediente1);

        Ingrediente ingrediente2 = Ingrediente.builder()
                .nombre("Alga nori")
                .build();
        ingredienteRepository.save(ingrediente2);

        Ingrediente ingrediente3 = Ingrediente.builder()
                .nombre("Wasabi")
                .build();
        ingredienteRepository.save(ingrediente3);

        Ingrediente ingrediente4 = Ingrediente.builder()
                .nombre("Palta")
                .build();
        ingredienteRepository.save(ingrediente4);

        Ingrediente ingrediente5 = Ingrediente.builder()
                .nombre("Salmon")
                .build();
        ingredienteRepository.save(ingrediente5);



    }
    private void crearUnidad(){
        Unidad unidadKG = Unidad.builder()
                .descripcion("KG")
                .build();
        unidadRepository.save(unidadKG);

        Unidad unidadGR = Unidad.builder()
                .descripcion("GR")
                .build();
        unidadRepository.save(unidadGR);

        Unidad unidadCantidad = Unidad.builder()
                .descripcion("Unidades")
                .build();
        unidadRepository.save(unidadCantidad);
    }
    private void crearItemIngrediente(){

        Utilizado Arroz = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(1))
                .cantidad(600)
                .receta(recetaService.findById(1))
                .unidad(unidadRepository.getById(2))
                .build();
        itemIngredienteRepository.save(Arroz);

        Utilizado AlgaNori = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(2))
                .cantidad(2)
                .receta(recetaService.findById(1))
                .unidad(unidadRepository.getById(3))
                .build();
        itemIngredienteRepository.save(AlgaNori);

        Utilizado Wasabi = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(3))
                .cantidad(100)
                .receta(recetaService.findById(1))
                .unidad(unidadRepository.getById(2))
                .build();
        itemIngredienteRepository.save(Wasabi);

        Utilizado Palta = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(4))
                .cantidad(250)
                .receta(recetaService.findById(1))
                .unidad(unidadRepository.getById(2))
                .build();
        itemIngredienteRepository.save(Palta);

        Utilizado Salmon = Utilizado.builder()
                .ingrediente(ingredienteRepository.getById(5))
                .cantidad(250)
                .receta(recetaService.findById(1))
                .unidad(unidadRepository.getById(2))
                .build();
        itemIngredienteRepository.save(Salmon);

    }
    private void crearUsuario(){

        Usuario usuario2 = Usuario.builder()
                .tipoUsuario(1)
                .avatar("http://res.cloudinary.com/recetasapd/image/upload/v1656007372/ib8rwuxyyt79n2tivcp7.jpg")
                .mail("gonzaloc@gmail.com")
                .nombre("Gonzalo Fernandez")
                .nickname("gonzalo.cocina")
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
        r.setTipo(tipoRepository.getById(2));
        r.setUsuario(usuarioRepository.getById(1));
        recetaService.save(r2);


        Receta r3 = recetaService.findById(3);
        r.setTipo(tipoRepository.getById(3));
        r.setUsuario(usuarioRepository.getById(1));
        recetaService.save(r3);

    }
    private void generarReceta() {

        //recetaService.generarRecetaConDistintasCantidades(1,"Doble",1);
       // recetaService.generarRecetaConDistintasPorciones(1,8,1);
        //recetaService.generarRecetaConDistintaCantidadIngrediente(1,1,1,500,1);

    }

    private void generarCalificaciones(){
        calificacionService.crearCalificacion(1,1,5.0F,"¡Quedan muy ricos!");
        calificacionService.crearCalificacion(2,1,4.0F,"¡Exquisito plato argentino!");
        calificacionService.crearCalificacion(3,1,3.0F,"Esta receta quedo muy bien, pero no cubre todas las porciones que indica");

    }

    private void agregarFavorita(){
        favoritaService.addFavorita(usuarioRepository.getById(1),recetaRepository.getById(1));
        favoritaService.addFavorita(usuarioRepository.getById(1),recetaRepository.getById(2));
        favoritaService.addFavorita(usuarioRepository.getById(1),recetaRepository.getById(3));

    }



}

