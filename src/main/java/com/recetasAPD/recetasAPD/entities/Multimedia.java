package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Multimedia")
@Getter
@Setter
@Builder
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
