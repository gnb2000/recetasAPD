package com.recetasAPD.recetasAPD.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "multimedia")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idContenido;
    private String tipo_contenido;
    private String extension;
    private String urlContenido;

    @ManyToOne
    @JoinColumn(name = "idPaso")
    private Paso paso;
}
