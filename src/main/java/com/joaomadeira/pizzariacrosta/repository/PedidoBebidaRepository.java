package com.joaomadeira.pizzariacrosta.repository;

import com.joaomadeira.pizzariacrosta.model.PedidoBebida;
import com.joaomadeira.pizzariacrosta.model.PedidoPizza;
import com.joaomadeira.pizzariacrosta.model.id.PedidoBebidaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoBebidaRepository extends JpaRepository<PedidoBebida, PedidoBebidaId> {
    List<PedidoBebida> findByPedidoId(Integer pedidoId);
}
