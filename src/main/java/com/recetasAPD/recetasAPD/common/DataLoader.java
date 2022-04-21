package com.recetasAPD.recetasAPD.common;

import com.recetasAPD.recetasAPD.entities.Tipo;
import com.recetasAPD.recetasAPD.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TipoRepository tipoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Tipo tipo = Tipo.builder()
                        .descripcion("ENSALADAS")
                        .build();
        tipoRepository.save(tipo);
    }
}
