package com.recetasAPD.recetasAPD.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pasos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paso {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaso;
    private Integer nroPaso;
    @Column(length = 2000)
    private String texto;

    @OneToMany(fetch = FetchType.EAGER,mappedBy= "paso",cascade = CascadeType.MERGE)
    private List<Multimedia> multimedia;

    @ManyToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}
