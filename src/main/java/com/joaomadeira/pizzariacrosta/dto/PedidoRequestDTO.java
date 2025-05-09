package com.joaomadeira.pizzariacrosta.dto;

import com.joaomadeira.pizzariacrosta.model.enums.StatusEntrega;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoRequestDTO {

    @NotNull
    private Integer clienteId;

    private Integer funcionarioAceitouId;

    @NotBlank
    private String endereco;

    private BigDecimal valorTotal;
    private LocalDateTime dataHora;
    private StatusEntrega status;
    private List<PedidoPizzasDTO> pizzas;
    private List<PedidoBebidasDTO> bebidas;
    private BigDecimal frete;

}
