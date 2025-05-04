package com.joaomadeira.pizzariacrosta.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TamanhoPizza {

    PEQUENA("pequena"),
    MEDIA("media"),
    GRANDE("grande"),
    GIGANTE("gigante");

    private final String valor;

    @Override
    public String toString() {
        return valor;
    }

}
