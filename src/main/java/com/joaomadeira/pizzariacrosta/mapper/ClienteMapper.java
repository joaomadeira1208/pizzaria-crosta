package com.joaomadeira.pizzariacrosta.mapper;

import com.joaomadeira.pizzariacrosta.dto.ClienteRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.ClienteResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Cliente;
import com.joaomadeira.pizzariacrosta.model.Pessoa;
import com.joaomadeira.pizzariacrosta.utils.SenhaUtils;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Pessoa toPessoa(ClienteRequestDTO dto) {
        return Pessoa.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .idade(dto.getIdade())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .senha(SenhaUtils.encode(dto.getSenha()))
                .build();
    }

    public Cliente toCliente(Pessoa pessoa) {
        return Cliente.builder()
                .pessoa(pessoa)
                .status(true)
                .build();
    }

    public ClienteResponseDTO toResponseDTO(Cliente cliente) {
        return ClienteResponseDTO.builder()
                .nome(cliente.getPessoa().getNome())
                .cpf(cliente.getPessoa().getCpf())
                .idade(cliente.getPessoa().getIdade())
                .telefone(cliente.getPessoa().getTelefone())
                .email(cliente.getPessoa().getEmail())
                .build();
    }

}
