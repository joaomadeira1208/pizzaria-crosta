package com.joaomadeira.pizzariacrosta.dto;

import com.joaomadeira.pizzariacrosta.model.enums.StatusEntrega;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlterarStatusPedidoDTO {

    @NotNull
    private StatusEntrega status;
}