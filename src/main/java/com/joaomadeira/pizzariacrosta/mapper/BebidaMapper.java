package com.joaomadeira.pizzariacrosta.mapper;

import com.joaomadeira.pizzariacrosta.dto.BebidaRequestDTO;
import com.joaomadeira.pizzariacrosta.dto.BebidaResponseDTO;
import com.joaomadeira.pizzariacrosta.model.Bebida;
import org.springframework.stereotype.Component;

@Component
public class BebidaMapper {

    public Bebida toEntity(BebidaRequestDTO bebidaRequestDTO) {
        return Bebida.builder()
                .nome(bebidaRequestDTO.getNome().toUpperCase())
                .preco(bebidaRequestDTO.getPreco())
                .estoque(bebidaRequestDTO.getEstoque())
                .status(true)
                .build();
    }


    public BebidaResponseDTO toResponseDTO(Bebida bebida) {
        return BebidaResponseDTO.builder()
                .id(bebida.getId())
                .nome(bebida.getNome())
                .preco(bebida.getPreco())
                .imageUrl(bebida.getUrlImagem())
                .build();
    }
}
