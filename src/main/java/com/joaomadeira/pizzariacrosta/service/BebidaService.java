package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.BebidaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.mapper.BebidaMapper;
import com.joaomadeira.pizzariacrosta.model.Bebida;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.repository.BebidaRepository;
import com.joaomadeira.pizzariacrosta.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BebidaService {

    private final BebidaRepository bebidaRepository;
    private final BebidaMapper bebidaMapper;
    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public BebidaResponseDTO cadastrarBebida(BebidaRequestDTO bebidaRequestDTO) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(bebidaRequestDTO.getFuncionarioExecutando());
        if (funcionario.isEmpty() || !funcionario.get().getCargo().equals("GERENTE")) {
            throw new IllegalArgumentException("Funcionário não encontrado ou não é gerente");
        }

        Optional<Bebida> bebidaExistente = bebidaRepository.findByNomeAndStatus(bebidaRequestDTO.getNome().toUpperCase(), true);
        if (bebidaExistente.isPresent()) {
            throw new IllegalArgumentException("Bebida já cadastrada");
        }

        Bebida bebida = bebidaMapper.toEntity(bebidaRequestDTO);
        bebidaRepository.save(bebida);
        return bebidaMapper.toResponseDTO(bebida);
    }

}
