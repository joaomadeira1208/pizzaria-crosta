package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.BebidaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.service.BebidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bebidas")
@RequiredArgsConstructor
public class BebidaController {

    private final BebidaService bebidaService;

    @PostMapping
    public ResponseEntity<BebidaResponseDTO> cadastrarBebida(@RequestBody BebidaRequestDTO bebidaRequestDTO) {
        BebidaResponseDTO response = bebidaService.cadastrarBebida(bebidaRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<BebidaResponseDTO>> disponiveis() {
        List<BebidaResponseDTO> response = bebidaService.consultarBebidas();
        return ResponseEntity.ok(response);
    }
}
