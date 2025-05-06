package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.PizzaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.mapper.PizzaMapper;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.model.Pizza;
import com.joaomadeira.pizzariacrosta.repository.FuncionarioRepository;
import com.joaomadeira.pizzariacrosta.repository.PizzaRepository;
import com.joaomadeira.pizzariacrosta.repository.custom.PizzaRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;
    private final PizzaIngredienteService pizzaIngredienteService;
    private final FuncionarioRepository funcionarioRepository;
    private final PizzaRepositoryCustom pizzaRepositoryCustom;

    @Transactional
    public PizzaResponseDTO cadastrarPizza(PizzaRequestDTO pizzaRequestDTO) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(pizzaRequestDTO.getFuncionarioExecutando());
        if (funcionario.isEmpty() || !funcionario.get().getCargo().equals("GERENTE")) {
            throw new IllegalArgumentException("Funcionário não encontrado ou não é gerente");
        }

        Optional<Pizza> pizzaExistente = pizzaRepository.findBySaborAndStatus(pizzaRequestDTO.getSabor().toUpperCase(), true);
        if (pizzaExistente.isPresent()) {
            throw new IllegalArgumentException("Pizza já cadastrada");
        }

        Pizza pizza = pizzaMapper.toEntity(pizzaRequestDTO);
        Pizza pizzaSalva = pizzaRepository.save(pizza);
        pizzaIngredienteService.cadastrarPizzaIngrediente(pizzaSalva, pizzaRequestDTO.getIdIngredientes());
        return pizzaMapper.toResponseDTO(pizza);
    }

    public List<PizzaResponseDTO> listarPizzas() {
        return pizzaRepositoryCustom.findAllByStatus();
    }
}
