package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.BaseResponse;
import com.joaomadeira.pizzariacrosta.dto.ClienteRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.LoginRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.LoginResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Cliente;
import com.joaomadeira.pizzariacrosta.model.Pessoa;
import com.joaomadeira.pizzariacrosta.repository.ClienteRepository;
import com.joaomadeira.pizzariacrosta.repository.FuncionarioRepository;
import com.joaomadeira.pizzariacrosta.repository.PessoaRepository;
import com.joaomadeira.pizzariacrosta.utils.SenhaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PessoaRepository pessoaRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public LoginResponseDTO login(LoginRequestDTO loginDTO ) {
        Pessoa pessoa = pessoaRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        if (SenhaUtils.matches(loginDTO.getSenha(), pessoa.getSenha())) {
            Optional<Cliente> cliente = clienteRepository.findById(pessoa.getId());
            if (cliente.isPresent()) {
                return LoginResponseDTO.builder()
                        .sucesso(Boolean.TRUE)
                        .tipoUsuario("CLIENTE")
                        .id(pessoa.getId())
                        .build();
            }
            else {
                return LoginResponseDTO.builder()
                        .sucesso(Boolean.TRUE)
                        .tipoUsuario("FUNCIONARIO")
                        .id(pessoa.getId())
                        .build();
            }
        }
        else {
            return LoginResponseDTO.builder()
                    .sucesso(Boolean.FALSE)
                    .build();
        }
    }

}
