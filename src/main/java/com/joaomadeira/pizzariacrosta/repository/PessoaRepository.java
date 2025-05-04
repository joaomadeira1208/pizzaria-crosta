package com.joaomadeira.pizzariacrosta.repository;

import com.joaomadeira.pizzariacrosta.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    Optional<Pessoa> findByEmail(String email);

}
