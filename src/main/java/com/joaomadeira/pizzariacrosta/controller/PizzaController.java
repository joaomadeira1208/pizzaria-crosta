package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.PizzaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PizzaResponseDTO>> listarPizzas() {
        List<PizzaResponseDTO> response = pizzaService.listarPizzas();
        return ResponseEntity.ok(response);
    }

}
