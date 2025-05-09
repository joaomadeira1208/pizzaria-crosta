package com.joaomadeira.pizzariacrosta.repository.impl;

import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.repository.custom.PizzaRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class PizzaRepositoryImpl implements PizzaRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<PizzaResponseDTO> findAllByStatus() {
        String sql = """
                SELECT p.id, p.sabor, p.preco, p.url_imagem, i.nome AS ingrediente
                FROM pizzas p
                JOIN pizza_ingredientes pi ON pi.pizza_id = p.id
                JOIN ingredientes i ON pi.ingrediente_id = i.id
                WHERE p.status = true AND i.status = true
                ORDER BY p.id
        """;

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<Integer, PizzaResponseDTO> pizzaMap = new LinkedHashMap<>();

        for(Map<String, Object> row: rows) {
            var url = row.get("url_imagem");
            Integer pizzaId = (Integer) row.get("id");
            String sabor = (String) row.get("sabor");
            BigDecimal preco = (BigDecimal) row.get("preco");
            String ingrediente = (String) row.get("ingrediente");
            String urlImagem = Objects.nonNull(url) ? (String) url : null;

            PizzaResponseDTO dto = pizzaMap.get(pizzaId);
            if (dto == null) {
                dto = PizzaResponseDTO.builder()
                        .id(pizzaId)
                        .sabor(sabor)
                        .preco(preco)
                        .imageUrl(urlImagem)
                        .ingredientes(new ArrayList<>())
                        .build();
                pizzaMap.put(pizzaId, dto);
            }
            dto.getIngredientes().add(ingrediente);
        }

        return new ArrayList<>(pizzaMap.values());
    }

}
