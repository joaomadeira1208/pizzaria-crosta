package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.ClienteRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.FuncionarioRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.FuncionarioResponseDTO;
import com.joaomadeira.pizzariacrosta.mapper.FuncionarioMapper;
import com.joaomadeira.pizzariacrosta.model.Funcionario;
import com.joaomadeira.pizzariacrosta.model.Pessoa;
import com.joaomadeira.pizzariacrosta.repository.FuncionarioRepository;
import com.joaomadeira.pizzariacrosta.repository.PessoaRepository;
import com.joaomadeira.pizzariacrosta.utils.SenhaUtils;
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
        Funcionario funcionarioCadastrando = funcionarioRepository.findById(funcionarioDTO.getIdFuncionarioExecutando())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        if(funcionarioCadastrando.getCargo() != null && "GERENTE".equals(funcionarioCadastrando.getCargo())) {
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

    public FuncionarioResponseDTO atualizarFuncionario(
            @Valid Integer idFuncionario, FuncionarioRequestDTO funcionarioDTO) {

        Funcionario funcionarioAtualizando = funcionarioRepository.findById(funcionarioDTO.getIdFuncionarioExecutando())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        if(!idFuncionario.equals(funcionarioDTO.getIdFuncionarioExecutando())
                && !"GERENTE".equals(funcionarioAtualizando.getCargo())) {
            throw new IllegalArgumentException("Funcionario não tem permissão");
        }

        if (!pessoaRepository.existsByCpf(funcionarioDTO.getCpf())) {
            throw new IllegalArgumentException("Funcionário não cadastrado");
        }
        Pessoa pessoa = funcionarioAtualizando.getPessoa();
        atualizarDadosPessoa(pessoa, funcionarioDTO);
        atualizarDadosFuncionario(funcionarioAtualizando, funcionarioDTO);
        pessoaRepository.save(pessoa);
        funcionarioRepository.save(funcionarioAtualizando);

        funcionarioAtualizando.setPessoa(pessoa);
        return funcionarioMapper.toResponseDTO(funcionarioAtualizando);

    }

    private void atualizarDadosPessoa(Pessoa pessoa, FuncionarioRequestDTO funcionarioRequestDTO) {
        if (funcionarioRequestDTO.getNome() != null) {
            pessoa.setNome(funcionarioRequestDTO.getNome());
        }
        if (funcionarioRequestDTO.getEmail() != null) {
            pessoa.setEmail(funcionarioRequestDTO.getEmail());
        }
        if (funcionarioRequestDTO.getCpf() != null) {
            pessoa.setCpf(funcionarioRequestDTO.getCpf());
        }
        if (funcionarioRequestDTO.getTelefone() != null) {
            pessoa.setTelefone(funcionarioRequestDTO.getTelefone());
        }
        if (funcionarioRequestDTO.getIdade() != null) {
            pessoa.setIdade(funcionarioRequestDTO.getIdade());
        }
        if (funcionarioRequestDTO.getSenha() != null) {
            String senhaCriptografada = SenhaUtils.encode(funcionarioRequestDTO.getSenha());
            pessoa.setSenha(senhaCriptografada);
        }
    }

    private void atualizarDadosFuncionario(Funcionario funcionario, FuncionarioRequestDTO funcionarioRequestDTO){
        if (funcionarioRequestDTO.getCargo() != null) {
            funcionario.setCargo(funcionarioRequestDTO.getCargo());
        }
        if (funcionarioRequestDTO.getTurno() != null) {
            funcionario.setTurno(funcionarioRequestDTO.getTurno());
        }
    }
}
