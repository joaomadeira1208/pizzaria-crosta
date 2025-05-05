package com.joaomadeira.pizzariacrosta.repository;

import com.joaomadeira.pizzariacrosta.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FuncionarioRepository  extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByPessoaCpf(String cpf);
}
