package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация для SWAGGER
 */

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI testcaseProject() {
        return new OpenAPI()
                .info(new Info()
                        .title("Тестовое задание")
                        .description("Сервис банковских переводов")
                        .version("0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact().name("Vladimir Vlasenko").email("v.g.vlasenko@yandex.ru").url(""))
                );
    }
}