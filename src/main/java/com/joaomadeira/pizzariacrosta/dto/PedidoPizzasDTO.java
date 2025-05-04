package com.joaomadeira.pizzariacrosta.dto;

import com.joaomadeira.pizzariacrosta.model.enums.TamanhoPizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoPizzasDTO {

    private Integer pizzaId;
    private Integer quantidade;
    private TamanhoPizza tamanho;

}
