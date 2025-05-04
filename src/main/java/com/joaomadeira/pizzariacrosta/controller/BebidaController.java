package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.BebidaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.service.BebidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bebidas")
@RequiredArgsConstructor
public class BebidaController {

    private final BebidaService bebidaService;

    @PostMapping
    public ResponseEntity<BebidaResponseDTO> cadastrarPizza(@RequestBody BebidaRequestDTO bebidaRequestDTO) {
        BebidaResponseDTO response = bebidaService.cadastrarBebida(bebidaRequestDTO);
        return ResponseEntity.ok(response);
    }

}
