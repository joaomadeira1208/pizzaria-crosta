package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.AlterarStatusPedidoDTO;
import com.joaomadeira.pizzariacrosta.dto.BaseResponse;
import com.joaomadeira.pizzariacrosta.dto.PedidoRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PedidoResponseDTO;
import com.joaomadeira.pizzariacrosta.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/por-cliente/{id}")
    public ResponseEntity<List<PedidoResponseDTO>> buscarPedidosPorCliente(@PathVariable Integer id) {
        List<PedidoResponseDTO> response = pedidoService.buscarPedidosPorCliente(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recuperar-status/{idPedido}")
    public ResponseEntity<BaseResponse> recuperarStatus(@PathVariable Integer idPedido) {
        BaseResponse response = pedidoService.recuperarStatus(idPedido);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/alterar-status/{idPedido}")
    public ResponseEntity<PedidoResponseDTO> alterarStatusPedido(@PathVariable Integer idPedido, @RequestBody AlterarStatusPedidoDTO pedidoDTO) {
        PedidoResponseDTO response = pedidoService.alterarStatusPedido(idPedido, pedidoDTO.getStatus());
        return ResponseEntity.status(200).body(response);
    }
}
