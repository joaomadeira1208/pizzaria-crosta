package com.joaomadeira.pizzariacrosta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joaomadeira.pizzariacrosta.model.enums.TamanhoPizza;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaResponseDTO {

    @NotNull
    private String sabor;



    @NotNull
    private BigDecimal preco;

    private TamanhoPizza tamanho;
    private Integer quantidade;

}
