package com.melnur.AdisyonTakipSistemi.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class OpenApiConfig {

    @Bean
    public OpenAPI adisyonTakipSistemiOpenApi(){
        return new OpenAPI ()
                .info(new Info().title("Adisyon Takip Sistemi API")
                        .description("Adisyon Takip Sistemi API documentation")
                        .version("1.0.0"));
    }
}
