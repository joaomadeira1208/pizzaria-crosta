package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.BaseResponse;
import com.joaomadeira.pizzariacrosta.service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<BaseResponse> cadastrarIngrediente(@RequestParam String nome, @RequestParam Integer funcionarioExecutando) {
        BaseResponse response = ingredienteService.cadastrarIngrediente(nome, funcionarioExecutando);
        return ResponseEntity.ok(response);
    }



}
