package com.joaomadeira.pizzariacrosta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.joaomadeira.pizzariacrosta.model.enums.StatusEntrega;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoResponseDTO {

    @NotNull
    private ClienteResponseDTO cliente;

    private FuncionarioResponseDTO funcionario;
    private BigDecimal valorTotal;
    private String endereco;
    private LocalDateTime dataHora;
    private StatusEntrega status;
    private List<PizzaResponseDTO> pizzas;
    private List<BebidaResponseDTO> bebidas;
    private Integer idPedido;
}
