package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.*;
import com.joaomadeira.pizzariacrosta.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> cadastrarFuncionario(
            @RequestBody @Valid FuncionarioRequestDTO funcionarioDTO) {
        FuncionarioResponseDTO response = funcionarioService.cadastrarFuncionario(funcionarioDTO);
        return ResponseEntity.status(201).body(response);

    }

    @PutMapping("/atualizar/{idFuncionario}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(
            @PathVariable Integer idFuncionario,
            @RequestBody FuncionarioRequestDTO funcionarioDTO) {

        FuncionarioResponseDTO response = funcionarioService.atualizarFuncionario(idFuncionario, funcionarioDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/alterar-Status/{idGerente}")
    public ResponseEntity<Boolean> alterarStatusFuncionario(
            @PathVariable Integer idGerente, @RequestBody FuncionarioAlterarStatusDTO funcionarioDTO) {

        Boolean response = funcionarioService.alterarStatusFuncionario(idGerente, funcionarioDTO);
        return ResponseEntity.ok(response);
    }
}
