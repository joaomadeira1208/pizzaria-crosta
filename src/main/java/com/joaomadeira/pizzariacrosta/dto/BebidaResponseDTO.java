package com.joaomadeira.pizzariacrosta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BebidaResponseDTO {

    @NotNull
    private String nome;

    @NotNull
    private BigDecimal preco;

    private Integer quantidade;

}
