package com.recetasAPD.recetasAPD.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="RecetaExt")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecetaExt {

    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecetaExt;
    private Integer estado;
    private LocalDateTime fecha;

    @OneToOne
    @JoinColumn(name = "idReceta")
    private Receta receta;
}
