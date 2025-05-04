package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.FuncionarioRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.FuncionarioResponseDTO;
import com.joaomadeira.pizzariacrosta.mapper.FuncionarioMapper;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.model.Pessoa;
import com.joaomadeira.pizzariacrosta.repository.FuncionarioRepository;
import com.joaomadeira.pizzariacrosta.repository.PessoaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final PessoaRepository pessoaRepository;
    private final FuncionarioMapper funcionarioMapper;

    @Transactional
    public FuncionarioResponseDTO cadastrarFuncionario(@Valid FuncionarioRequestDTO funcionarioDTO) {
        if(funcionarioDTO.getCargo() != null && "Gerente".equals(funcionarioDTO.getCargo())) {
            if (pessoaRepository.existsByCpf(funcionarioDTO.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado");
            }

            if (pessoaRepository.existsByEmail(funcionarioDTO.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado");
            }
            Pessoa pessoa = funcionarioMapper.toPessoa(funcionarioDTO);
            pessoaRepository.save(pessoa);

            Funcionario funcionario = funcionarioMapper.toFuncionario(pessoa, funcionarioDTO);
            Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

            return funcionarioMapper.toResponseDTO(funcionarioSalvo);
        }
        throw new IllegalArgumentException("Funcionário não tem permissão");
    }
}
