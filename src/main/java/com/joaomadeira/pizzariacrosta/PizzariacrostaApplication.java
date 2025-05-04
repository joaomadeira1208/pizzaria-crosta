package com.joaomadeira.pizzariacrosta;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@Log4j2
public class PizzariacrostaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzariacrostaApplication.class, args);
    }

    @Bean
    public ApplicationRunner failFast(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                log.info("Conectado ao banco com sucesso.");
            } catch (SQLException e) {
                log.error("Falha na conexão com o banco: " + e.getMessage());
                System.exit(1);
            }
        };
    }

}


