package com.joaomadeira.pizzariacrosta.repository.custom;

import com.joaomadeira.pizzariacrosta.dto.PedidoResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Pedido;

import java.util.List;

public interface PedidoRepositoryCustom {
    List<PedidoResponseDTO> buscarPizzaBebidaPorPedido(List<Pedido> pedidos);
}
