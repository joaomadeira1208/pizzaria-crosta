package com.joaomadeira.pizzariacrosta.repository.impl;

import com.joaomadeira.pizzariacrosta.dto.PizzaResponseDTO;
import com.joaomadeira.pizzariacrosta.repository.custom.PizzaRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PizzaRepositoryImpl implements PizzaRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<PizzaResponseDTO> findAllByStatus() {
        String sql = """
                SELECT p.id, p.sabor, p.preco, i.nome AS ingrediente
                FROM pizzas p
                JOIN pizza_ingredientes pi ON pi.pizza_id = p.id
                JOIN ingredientes i ON pi.ingrediente_id = i.id
                WHERE p.status = true AND i.status = true
                ORDER BY p.id
        """;

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<Integer, PizzaResponseDTO> pizzaMap = new LinkedHashMap<>();

        for(Map<String, Object> row: rows) {
            Integer pizzaId = (Integer) row.get("id");
            String sabor = (String) row.get("sabor");
            BigDecimal preco = (BigDecimal) row.get("preco");
            String ingrediente = (String) row.get("ingrediente");

            PizzaResponseDTO dto = pizzaMap.get(pizzaId);
            if (dto == null) {
                dto = PizzaResponseDTO.builder()
                        .sabor(sabor)
                        .preco(preco)
                        .ingredientes(new ArrayList<>())
                        .build();
                pizzaMap.put(pizzaId, dto);
            }
            dto.getIngredientes().add(ingrediente);
        }

        return new ArrayList<>(pizzaMap.values());
    }

}
