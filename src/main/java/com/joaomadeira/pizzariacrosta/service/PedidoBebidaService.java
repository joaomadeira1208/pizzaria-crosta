package com.joaomadeira.pizzariacrosta.service;

import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.dto.PedidoBebidasDTO;
import com.joaomadeira.pizzariacrosta.model.*;
import com.joaomadeira.pizzariacrosta.model.id.PedidoBebidaId;
import com.joaomadeira.pizzariacrosta.repository.PedidoBebidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoBebidaService {

    private final PedidoBebidaRepository pedidoBebidaRepository;

    public List<BebidaResponseDTO> salvarPedidoBebidas(Pedido pedido, List<PedidoBebidasDTO> bebidaDTOs, List<Bebida> bebidas) {
        Map<Integer, Bebida> bebidaMap = bebidas.stream()
                .collect(Collectors.toMap(Bebida::getId, p -> p));

        List<PedidoBebida> pedidoBebidas = bebidaDTOs.stream()
                .map(dto -> {
                    Bebida bebida = bebidaMap.get(dto.getBebidaId());
                    return PedidoBebida.builder()
                            .id(new PedidoBebidaId(pedido.getId(), bebida.getId()))
                            .pedido(pedido)
                            .bebida(bebida)
                            .quantidade(dto.getQuantidade())
                            .build();
                })
                .toList();

        pedidoBebidaRepository.saveAll(pedidoBebidas);

        return pedidoBebidas.stream()
                .map(pb -> BebidaResponseDTO.builder()
                        .nome(pb.getBebida().getNome())
                        .preco(pb.getBebida().getPreco())
                        .quantidade(pb.getQuantidade())
                        .build()
                )
                .toList();
    }

}
