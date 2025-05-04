package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.*;
import com.joaomadeira.pizzariacrosta.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> cadastrarFuncionario(@RequestBody @Valid FuncionarioRequestDTO funcionarioDTO) {
        FuncionarioResponseDTO response = funcionarioService.cadastrarFuncionario(funcionarioDTO);
        return ResponseEntity.status(201).body(response);

    }
}
