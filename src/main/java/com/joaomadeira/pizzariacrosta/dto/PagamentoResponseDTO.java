package com.joaomadeira.pizzariacrosta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoResponseDTO {

    private String clientSecret;

}
