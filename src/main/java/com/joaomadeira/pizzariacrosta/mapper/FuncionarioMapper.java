package com.joaomadeira.pizzariacrosta.mapper;

import com.joaomadeira.pizzariacrosta.dto.FuncionarioRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.FuncionarioResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.model.Pessoa;
import com.joaomadeira.pizzariacrosta.utils.SenhaUtils;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper {
    public Pessoa toPessoa(FuncionarioRequestDTO dto) {
        return Pessoa.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .idade(dto.getIdade())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .senha(SenhaUtils.encode(dto.getSenha()))
                .build();
    }

    public Funcionario toFuncionario(Pessoa pessoa, FuncionarioRequestDTO dto) {
        return Funcionario.builder()
                .pessoa(pessoa)
                .cargo(dto.getCargo())
                .turno(dto.getTurno())
                .status(true)
                .build();
    }

    public FuncionarioResponseDTO toResponseDTO(Funcionario funcionario) {
        return FuncionarioResponseDTO.builder()
                .nome(funcionario.getPessoa().getNome())
                .cpf(funcionario.getPessoa().getCpf())
                .idade(funcionario.getPessoa().getIdade())
                .telefone(funcionario.getPessoa().getTelefone())
                .email(funcionario.getPessoa().getEmail())
                .cargo(funcionario.getCargo().toUpperCase())
                .turno(funcionario.getTurno())
                .build();
    }
}
