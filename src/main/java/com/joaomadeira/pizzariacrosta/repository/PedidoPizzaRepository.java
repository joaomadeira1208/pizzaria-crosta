package com.joaomadeira.pizzariacrosta.repository;

import com.joaomadeira.pizzariacrosta.model.PedidoPizza;
import com.joaomadeira.pizzariacrosta.model.id.PedidoPizzaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoPizzaRepository extends JpaRepository<PedidoPizza, PedidoPizzaId> {
    List<PedidoPizza> findByPedidoId(Integer pedidoId);
}
