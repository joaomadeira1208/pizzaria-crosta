package com.joaomadeira.pizzariacrosta.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Value("${cors.origem}")
    private String origem;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todos os endpoints
                        .allowedOrigins(origem) // Permite chamadas dessa origem
                        .allowedMethods("*") // GET, POST, PUT, DELETE, etc.
                        .allowedHeaders("*");
            }
        };
    }

}
