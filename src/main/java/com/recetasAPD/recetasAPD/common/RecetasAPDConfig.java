package com.recetasAPD.recetasAPD.common;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RecetasAPDConfig {

    @Bean //Esto es para poder hacer un Autowired desde otras clases
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
