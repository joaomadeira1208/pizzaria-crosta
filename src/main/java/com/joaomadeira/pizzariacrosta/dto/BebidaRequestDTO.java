package com.joaomadeira.pizzariacrosta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BebidaRequestDTO {

    @NotNull
    private String nome;

    private Integer estoque;
    private BigDecimal preco;
    private Integer funcionarioExecutando;

}
