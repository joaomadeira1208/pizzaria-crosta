package com.joaomadeira.pizzariacrosta.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TamanhoPizza {
    PEQUENA(0.8),
    MEDIA(1.0),
    GRANDE(1.3),
    GIGANTE(1.6);

    private final double multiplicador;
}
