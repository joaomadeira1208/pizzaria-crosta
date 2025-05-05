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
public class FuncionarioResponseDTO {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Min(0)
    private Integer idade;

    @NotBlank
    @Size(max = 20)
    private String telefone;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String cargo;

    @NotNull
    @Min(0)
    private Integer turno;

    private boolean status;

}
