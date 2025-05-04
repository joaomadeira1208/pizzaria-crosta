package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.PedidoPizzasDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Pedido;
import com.joaomadeira.pizzariacrosta.model.PedidoPizza;
import com.joaomadeira.pizzariacrosta.model.Pizza;
import com.joaomadeira.pizzariacrosta.model.id.PedidoPizzaId;
import com.joaomadeira.pizzariacrosta.repository.PedidoPizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoPizzaService {

    private final PedidoPizzaRepository pedidoPizzaRepository;

    public List<PizzaResponseDTO> salvarPedidoPizzas(Pedido pedido, List<PedidoPizzasDTO> pizzaDTOs, List<Pizza> pizzas) {
        Map<Integer, Pizza> pizzaMap = pizzas.stream()
                .collect(Collectors.toMap(Pizza::getId, p -> p));

        List<PedidoPizza> pedidoPizzas = pizzaDTOs.stream()
                .map(dto -> {
                    Pizza pizza = pizzaMap.get(dto.getPizzaId());
                    return PedidoPizza.builder()
                            .id(new PedidoPizzaId(pedido.getId(), pizza.getId()))
                            .pedido(pedido)
                            .pizza(pizza)
                            .tamanho(dto.getTamanho())
                            .quantidade(dto.getQuantidade())
                            .build();
                })
                .toList();

        pedidoPizzaRepository.saveAll(pedidoPizzas);

        return pedidoPizzas.stream()
                .map(pp -> PizzaResponseDTO.builder()
                        .sabor(pp.getPizza().getSabor())
                        .tamanho(pp.getTamanho())
                        .preco(pp.getPizza().getPreco())
                        .quantidade(pp.getQuantidade())
                        .build()
                )
                .toList();
    }

}
