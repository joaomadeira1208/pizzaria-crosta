package com.joaomadeira.pizzariacrosta.repository;

import com.joaomadeira.pizzariacrosta.model.Cliente;
import com.joaomadeira.pizzariacrosta.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByCliente(Cliente cliente);
}
