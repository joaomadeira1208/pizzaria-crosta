package com.joaomadeira.pizzariacrosta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joaomadeira.pizzariacrosta.model.enums.TamanhoPizza;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaResponseDTO {

    private Integer id;
    private String sabor;
    private BigDecimal preco;
    private TamanhoPizza tamanho;
    private String imageUrl;
    private Integer quantidade;
    private List<String> ingredientes;

}
