package com.joaomadeira.pizzariacrosta.controller;

import com.joaomadeira.pizzariacrosta.dto.BaseResponse;
import com.joaomadeira.pizzariacrosta.dto.ClienteRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.ClienteResponseDTO;
import com.joaomadeira.pizzariacrosta.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@RequestBody @Valid ClienteRequestDTO clienteDTO) {
        ClienteResponseDTO response = clienteService.cadastrarCliente(clienteDTO);
        return ResponseEntity.status(201).body(response);

    }

    @PostMapping("/alterar-status/{id}")
    public ResponseEntity<BaseResponse> alterarStatusCliente(@PathVariable Integer id) {
        String response = clienteService.alterarStatus(id);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),response));
    }

}
