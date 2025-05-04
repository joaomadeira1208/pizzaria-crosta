package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.PizzaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public ResponseEntity<PizzaResponseDTO> cadastrarPizza(@RequestBody PizzaRequestDTO pizzaRequestDTO) {
        PizzaResponseDTO response = pizzaService.cadastrarPizza(pizzaRequestDTO);
        return ResponseEntity.ok(response);
    }

}
