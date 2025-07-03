package com.syedsaifuddin045.tasks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tasks API")
                        .version("1.0")
                        .description("Task Management API with Spring Boot 3 and Springdoc")
                        .contact(new Contact()
                                .name("Syed Saifuddin")
                                .email("syedsaifuddin0911@gmail.com")));
    }
}
