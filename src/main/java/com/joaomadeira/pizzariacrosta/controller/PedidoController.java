package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.PedidoRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PedidoResponseDTO;
import com.joaomadeira.pizzariacrosta.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrarPedido(@RequestBody PedidoRequestDTO pedidoDTO) {
        PedidoResponseDTO response = pedidoService.cadastrarPedido(pedidoDTO);
        return ResponseEntity.status(201).body(response);
    }

}
