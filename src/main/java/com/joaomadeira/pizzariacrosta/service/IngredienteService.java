package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.BaseResponse;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.model.Ingrediente;
import com.joaomadeira.pizzariacrosta.repository.FuncionarioRepository;
import com.joaomadeira.pizzariacrosta.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public BaseResponse cadastrarIngrediente(String nomeIngrediente, Integer funcionarioExecutando) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioExecutando);
        if (funcionario.isEmpty() || !funcionario.get().getCargo().equals("GERENTE")) {
            throw new IllegalArgumentException("Funcionário não encontrado ou não é gerente");
        }

        if (ingredienteRepository.existsByNome(nomeIngrediente.toUpperCase())) {
            throw new IllegalArgumentException("Ingrediente já cadastrado");
        }

        Ingrediente ingrediente = Ingrediente.builder()
                .nome(nomeIngrediente.toUpperCase())
                .status(true)
                .build();
        ingredienteRepository.save(ingrediente);

        return BaseResponse.builder()
                .response("Ingrediente cadastrado com sucesso")
                .status(201)
                .build();
    }

}
