package com.zipple.common.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(auth());
    }

    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            openApi.info(
                    new Info()
                            .title("Zipple API")
                            .description("API documentation")
                            .version("v1.0.0")
                            .contact(
                                    new Contact()
                                            .name("Donghwi")
                                            .email("tnqlsdld0222@gmail.com")
                            )
            );
        };
    }

    private Components auth() {
        return new Components()
                .addSecuritySchemes("auth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization"));
    }
}
