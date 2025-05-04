package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.*;
import com.joaomadeira.pizzariacrosta.mapper.PedidoMapper;
import com.joaomadeira.pizzariacrosta.model.*;
import com.joaomadeira.pizzariacrosta.model.enums.StatusEntrega;
import com.joaomadeira.pizzariacrosta.repository.*;
import com.joaomadeira.pizzariacrosta.repository.custom.PedidoRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoRepositoryCustom pedidoRepositoryCustom;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PizzaRepository pizzaRepository;
    private final BebidaRepository bebidaRepository;
    private final PedidoMapper pedidoMapper;
    private final PedidoPizzaService pedidoPizzaService;
    private final PedidoBebidaService pedidoBebidaService;

    @Transactional
    public PedidoResponseDTO cadastrarPedido(PedidoRequestDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(pedidoDTO.getFuncionarioAceitouId())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        List<Integer> pizzasIds = pedidoDTO.getPizzas().stream()
                .map(PedidoPizzasDTO::getPizzaId)
                .toList();

        List<Integer> bebidasIds = pedidoDTO.getBebidas().stream()
                .map(PedidoBebidasDTO::getBebidaId)
                .toList();

        List<Pizza> pizzas = pizzaRepository.findAllById(pizzasIds);
        List<Bebida> bebidas = bebidaRepository.findAllById(bebidasIds);

        if (pizzas.size() != pizzasIds.size() || bebidas.size() != bebidasIds.size()) {
            throw new IllegalArgumentException("Uma ou mais pizzas não foram encontradas");
        }

        pedidoDTO.setStatus(StatusEntrega.PENDENTE);
        pedidoDTO.setDataHora(LocalDateTime.now());

        Pedido pedido = pedidoMapper.toEntity(pedidoDTO, cliente, funcionario);
        calcularValorTotal(pedido, pizzas, bebidas, pedidoDTO);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        List<PizzaResponseDTO> pizzaResponseDTOs = pedidoPizzaService.salvarPedidoPizzas(pedidoSalvo, pedidoDTO.getPizzas(), pizzas);
        List<BebidaResponseDTO> bebidaResponseDTOs = pedidoBebidaService.salvarPedidoBebidas(pedidoSalvo, pedidoDTO.getBebidas(), bebidas);

        return pedidoMapper.toDTO(pedidoSalvo, pizzaResponseDTOs, bebidaResponseDTOs);
    }

    private void calcularValorTotal(Pedido pedido, List<Pizza> pizzas, List<Bebida> bebidas, PedidoRequestDTO pedidoRequestDTO) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        Map<Integer, Pizza> pizzaMap = pizzas.stream()
                .collect(Collectors.toMap(Pizza::getId, p -> p));

        Map<Integer, Bebida> bebidaMap = bebidas.stream()
                .collect(Collectors.toMap(Bebida::getId, b -> b));

        for (PedidoPizzasDTO dto : pedidoRequestDTO.getPizzas()) {
            Pizza pizza = pizzaMap.get(dto.getPizzaId());
            BigDecimal precoBase = pizza.getPreco();
            BigDecimal multiplicador = BigDecimal.valueOf(dto.getTamanho().getMultiplicador());
            BigDecimal precoFinal = precoBase.multiply(multiplicador);
            valorTotal = valorTotal.add(precoFinal.multiply(BigDecimal.valueOf(dto.getQuantidade())));
        }

        for (PedidoBebidasDTO dto : pedidoRequestDTO.getBebidas()) {
            Bebida bebida = bebidaMap.get(dto.getBebidaId());
            BigDecimal preco = bebida.getPreco();
            valorTotal = valorTotal.add(preco.multiply(BigDecimal.valueOf(dto.getQuantidade())));
        }

        pedido.setValorTotal(valorTotal);
    }

    public List<PedidoResponseDTO> buscarPedidosPorCliente(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        List<Pedido> pedidos = pedidoRepository.findAllByCliente(cliente);


        return pedidoRepositoryCustom.buscarPizzaBebidaPorPedido(pedidos);
    }

    public BaseResponse recuperarStatus(Integer idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        return BaseResponse.builder()
                .response(pedido.getStatusEntrega().toString())
                .status(200)
                .build();
    }
}
