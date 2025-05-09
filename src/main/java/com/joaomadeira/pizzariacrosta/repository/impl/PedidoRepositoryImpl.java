package com.joaomadeira.pizzariacrosta.repository.impl;

import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.dto.PedidoResponseDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.mapper.BebidaMapper;
import com.joaomadeira.pizzariacrosta.mapper.ClienteMapper;
import com.joaomadeira.pizzariacrosta.mapper.FuncionarioMapper;
import com.joaomadeira.pizzariacrosta.model.Pedido;
import com.joaomadeira.pizzariacrosta.model.enums.TamanhoPizza;
import com.joaomadeira.pizzariacrosta.repository.custom.PedidoRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;
    private final ClienteMapper clienteMapper;
    private final FuncionarioMapper funcionarioMapper;

    @Override
    public List<PedidoResponseDTO> buscarPizzaBebidaPorPedido(List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) return List.of();

        String sql = """
        SELECT 
            p.id as pedido_id,
            piz.sabor as pizza_sabor, 
            piz.preco as pizza_preco, 
            pp.quantidade as pizza_quantidade,
            pp.tamanho as pizza_tamanho,
            b.id as bebida_id,
            b.nome as bebida_nome,
            b.preco as bebida_preco,
            pb.quantidade as bebida_quantidade
        FROM pedidos p
        LEFT JOIN pedido_pizzas pp ON p.id = pp.pedido_id
        LEFT JOIN pizzas piz ON pp.pizza_id = piz.id
        LEFT JOIN pedido_bebidas pb ON p.id = pb.pedido_id
        LEFT JOIN bebidas b ON pb.bebida_id = b.id
        WHERE p.id IN (%s)
    """;

        String ids = pedidos.stream()
                .map(p -> String.valueOf(p.getId()))
                .collect(Collectors.joining(","));

        sql = String.format(sql, ids);

        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, PedidoResponseDTO> pedidoMap = new HashMap<>();

            while (rs.next()) {
                int pedidoId = rs.getInt("pedido_id");

                PedidoResponseDTO dto = pedidoMap.computeIfAbsent(pedidoId, id -> {
                    Pedido pedido = pedidos.stream()
                            .filter(p -> p.getId().equals(id))
                            .findFirst()
                            .orElseThrow();

                    return PedidoResponseDTO.builder()
                            .idPedido(pedidoId)
                            .cliente(clienteMapper.toResponseDTO(pedido.getCliente()))
                            .valorTotal(pedido.getValorTotal())
                            .endereco(pedido.getEnderecoEntrega())
                            .dataHora(pedido.getDataHora())
                            .status(pedido.getStatusEntrega())
                            .pizzas(new ArrayList<>())
                            .bebidas(new ArrayList<>())
                            .build();
                });

                if (rs.getString("pizza_sabor") != null) {
                    dto.getPizzas().add(PizzaResponseDTO.builder()
                            .sabor(rs.getString("pizza_sabor"))
                            .preco(rs.getBigDecimal("pizza_preco"))
                            .tamanho(TamanhoPizza.valueOf(rs.getString("pizza_tamanho")))
                            .quantidade(rs.getInt("pizza_quantidade"))
                            .build());
                }

                if (rs.getString("bebida_nome") != null) {
                    dto.getBebidas().add(BebidaResponseDTO.builder()
                            .nome(rs.getString("bebida_nome"))
                            .preco(rs.getBigDecimal("bebida_preco"))
                            .quantidade(rs.getInt("bebida_quantidade"))
                            .build());
                }
            }

            return new ArrayList<>(pedidoMap.values());
        });
    }

}
