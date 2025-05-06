package com.joaomadeira.pizzariacrosta.repository.custom;

import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;

import java.util.List;

public interface PizzaRepositoryCustom {

    List<PizzaResponseDTO> findAllByStatus();

}
