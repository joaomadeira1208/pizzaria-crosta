package com.joaomadeira.pizzariacrosta.mapper;

import com.joaomadeira.pizzariacrosta.dto.PizzaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaMapper {

    public Pizza toEntity(PizzaRequestDTO pizzaRequestDTO) {
        return Pizza.builder()
                .sabor(pizzaRequestDTO.getSabor().toUpperCase())
                .preco(pizzaRequestDTO.getPreco())
                .status(true)
                .build();
    }

    public PizzaResponseDTO toResponseDTO(Pizza pizza) {
        return PizzaResponseDTO.builder()
                .sabor(pizza.getSabor())
                .preco(pizza.getPreco())
                .build();
    }

}
