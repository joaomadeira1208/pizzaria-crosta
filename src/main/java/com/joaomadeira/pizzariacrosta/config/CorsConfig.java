package com.joaomadeira.pizzariacrosta.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

    @Value("${cors.origem}")
    private final String ORIGEM;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todos os endpoints
                        .allowedOrigins(ORIGEM) // Permite chamadas dessa origem
                        .allowedMethods("*") // GET, POST, PUT, DELETE, etc.
                        .allowedHeaders("*");
            }
        };
    }

}
