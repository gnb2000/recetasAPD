package com.recetasAPD.recetasAPD.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Multimedia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idMultimedia;
    private String tipoContenido;
    private String extension;
    private String urlContenido;

    @ManyToOne
    @JoinColumn(name = "idPaso")
    private Paso paso;
}
