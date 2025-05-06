package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.PagamentoRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.PagamentoResponseDTO;
import com.joaomadeira.pizzariacrosta.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping("/intencao")
    public ResponseEntity<PagamentoResponseDTO> criarIntencaoPagamento(@RequestBody PagamentoRequestDTO dto) {
        PagamentoResponseDTO response = pagamentoService.criarIntencaoPagamento(dto, "brl");
        return ResponseEntity.ok(response);
    }

}
