package com.recetasAPD.recetasAPD.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotValidNicknameExceptionResponse {

    private String message;
    private List<String> aliasRecomendados;
}
