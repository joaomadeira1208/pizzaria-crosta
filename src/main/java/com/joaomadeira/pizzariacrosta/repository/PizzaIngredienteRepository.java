package com.joaomadeira.pizzariacrosta.repository;

import com.joaomadeira.pizzariacrosta.model.PizzaIngrediente;
import com.joaomadeira.pizzariacrosta.model.id.PizzaIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaIngredienteRepository extends JpaRepository<PizzaIngrediente, PizzaIngredienteId> {
}
