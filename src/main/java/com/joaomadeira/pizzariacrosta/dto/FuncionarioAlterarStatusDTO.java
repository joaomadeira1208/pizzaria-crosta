package com.joaomadeira.pizzariacrosta.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioAlterarStatusDTO {
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    private Boolean status;
}
