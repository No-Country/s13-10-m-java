package com.nocountrys13.ecoapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPI30Configuration {
    private static final String API_KEY = "apiKey";
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(API_KEY, apiKeySecuritySchema()))
                .info(new Info().title("API - GreenPoint").description(
                        "RESTful services documentation with OpenAPI 3."))
                .addSecurityItem(new SecurityRequirement().addList(API_KEY));
    }
    public SecurityScheme apiKeySecuritySchema() {
        return new SecurityScheme()
                .name("authorization")
                .description("Description about the TOKEN")
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.APIKEY);
    }
}
