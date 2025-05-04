package com.joaomadeira.pizzariacrosta.dto;

import lombok.Builder;

@Builder
public record BaseResponse(int status, String mensagem) {}