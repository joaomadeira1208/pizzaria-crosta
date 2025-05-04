package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.BaseResponse;
import com.joaomadeira.pizzariacrosta.dto.ClienteRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.ClienteResponseDTO;
import com.joaomadeira.pizzariacrosta.mapper.ClienteMapper;
import com.joaomadeira.pizzariacrosta.model.Cliente;
import com.joaomadeira.pizzariacrosta.model.Pessoa;
import com.joaomadeira.pizzariacrosta.repository.ClienteRepository;
import com.joaomadeira.pizzariacrosta.repository.PessoaRepository;
import com.joaomadeira.pizzariacrosta.utils.SenhaUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponseDTO cadastrarCliente(@Valid ClienteRequestDTO clienteDTO) {
        if (pessoaRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        if (pessoaRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        Pessoa pessoa = clienteMapper.toPessoa(clienteDTO);
        pessoaRepository.save(pessoa);

        Cliente cliente = clienteMapper.toCliente(pessoa);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(clienteSalvo);
    }

    public String alterarStatus(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        cliente.setStatus(!cliente.isStatus());
        clienteRepository.save(cliente);

        return cliente.isStatus() ? "Cliente ativado com sucesso" : "Cliente inativado com sucesso";
    }

    public ClienteResponseDTO buscarClientePorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        return clienteMapper.toResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizarDadosCliente(ClienteRequestDTO clienteDTO, Integer clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Pessoa pessoa = cliente.getPessoa();
        atualizarDados(pessoa, clienteDTO);
        pessoaRepository.save(pessoa);

        cliente.setPessoa(pessoa);
        return clienteMapper.toResponseDTO(cliente);
    }

    private void atualizarDados(Pessoa pessoa, ClienteRequestDTO clienteRequestDTO) {
        if (clienteRequestDTO.getNome() != null) {
            pessoa.setNome(clienteRequestDTO.getNome());
        }
        if (clienteRequestDTO.getEmail() != null) {
            pessoa.setEmail(clienteRequestDTO.getEmail());
        }
        if (clienteRequestDTO.getCpf() != null) {
            pessoa.setCpf(clienteRequestDTO.getCpf());
        }
        if (clienteRequestDTO.getTelefone() != null) {
            pessoa.setTelefone(clienteRequestDTO.getTelefone());
        }
        if (clienteRequestDTO.getIdade() != null) {
            pessoa.setIdade(clienteRequestDTO.getIdade());
        }
        if (clienteRequestDTO.getSenha() != null) {
            String senhaCriptografada = SenhaUtils.encode(clienteRequestDTO.getSenha());
            pessoa.setSenha(senhaCriptografada);
        }
    }

}
