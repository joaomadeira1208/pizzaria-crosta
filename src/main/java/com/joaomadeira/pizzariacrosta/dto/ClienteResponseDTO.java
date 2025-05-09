package com.joaomadeira.pizzariacrosta.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteResponseDTO {


    @Size(max = 100)
    private String nome;


    @Size(min = 11, max = 11)
    private String cpf;


    @Min(0)
    private Integer idade;


    @Size(max = 20)
    private String telefone;


    @Size(max = 100)
    private String email;

}
