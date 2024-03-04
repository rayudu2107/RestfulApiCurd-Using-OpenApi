package com.restfulapicrud.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDocumentation {

    Info info() {
        return new Info().title("Welcome To Student Management System")
                .version("1.0")
                .description("Welcome To Student Management System API built using Spring-Boot and MySql database");
    }

 

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(info());
    }
}
