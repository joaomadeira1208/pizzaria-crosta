package com.joaomadeira.pizzariacrosta.mapper;

import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.dto.PedidoRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PedidoResponseDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Cliente;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.model.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final ClienteMapper clienteMapper;
    private final FuncionarioMapper funcionarioMapper;

    public Pedido toEntity(PedidoRequestDTO pedidoDTO, Cliente cliente) {
        return Pedido.builder()
                .cliente(cliente)
                .dataHora(pedidoDTO.getDataHora())
                .statusEntrega(pedidoDTO.getStatus())
                .valorTotal(pedidoDTO.getValorTotal())
                .enderecoEntrega(pedidoDTO.getEndereco().toUpperCase())
                .build();
    }

    public PedidoResponseDTO toDTO(Pedido pedido, List<PizzaResponseDTO> pizzaResponseDTO, List<BebidaResponseDTO> bebidaResponseDTO) {
        return PedidoResponseDTO.builder()
                .cliente(clienteMapper.toResponseDTO(pedido.getCliente()))
                .dataHora(pedido.getDataHora())
                .endereco(pedido.getEnderecoEntrega())
                .status(pedido.getStatusEntrega())
                .valorTotal(pedido.getValorTotal())
                .pizzas(pizzaResponseDTO)
                .bebidas(bebidaResponseDTO)
                .build();
    }

}
