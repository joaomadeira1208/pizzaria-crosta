package com.joaomadeira.pizzariacrosta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaRequestDTO {

    @NotNull
    private String sabor;

    private BigDecimal preco;
    private Integer funcionarioExecutando;
    private List<Integer> idIngredientes;

}
