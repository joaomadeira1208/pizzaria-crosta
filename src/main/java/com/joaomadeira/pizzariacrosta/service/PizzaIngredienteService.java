package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.model.Ingrediente;
import com.joaomadeira.pizzariacrosta.model.Pizza;
import com.joaomadeira.pizzariacrosta.model.PizzaIngrediente;
import com.joaomadeira.pizzariacrosta.model.id.PizzaIngredienteId;
import com.joaomadeira.pizzariacrosta.repository.IngredienteRepository;
import com.joaomadeira.pizzariacrosta.repository.PizzaIngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaIngredienteService {

    private final PizzaIngredienteRepository pizzaIngredienteRepository;
    private final IngredienteRepository ingredienteRepository;

    public void cadastrarPizzaIngrediente(Pizza pizza, List<Integer> idIngredientes) {
        List<Ingrediente> ingredientes = ingredienteRepository.findAllById(idIngredientes);

        if (ingredientes.size() != idIngredientes.size()) {
            throw new IllegalArgumentException("Um ou mais ingredientes não encontrados");
        }

        List<PizzaIngrediente> pizzaIngredientes = ingredientes.stream()
                .map(ingrediente -> PizzaIngrediente.builder()
                        .id(new PizzaIngredienteId(pizza.getId(), ingrediente.getId()))
                        .pizza(pizza)
                        .ingrediente(ingrediente)
                        .build())
                .toList();

        pizzaIngredienteRepository.saveAll(pizzaIngredientes);

    }

}
