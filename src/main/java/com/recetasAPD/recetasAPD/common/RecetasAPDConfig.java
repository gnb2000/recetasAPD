package com.recetasAPD.recetasAPD.common;

import com.cloudinary.Cloudinary;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
public class RecetasAPDConfig {

    @Bean //Esto es para poder hacer un Autowired desde otras clases
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "recetasapd");
        config.put("api_key", "266513888924289");
        config.put("api_secret", "DSgLD_COWnInQS_ZrOJDsgTrCCk");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
