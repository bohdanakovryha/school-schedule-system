package com.example.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "oauth2Scheme";

        Scopes scopes = new Scopes()
                .addString("openid", "OpenID scope")
                .addString("email", "Access email")
                .addString("profile", "Access profile");

        return new OpenAPI()
                .info(new Info()
                        .title("School Schedule API")
                        .version("1.0")
                        .description("REST API для розкладу вчителів та класів"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .flows(new OAuthFlows()
                                                .authorizationCode(new OAuthFlow()
                                                        .authorizationUrl("https://accounts.google.com/o/oauth2/auth")
                                                        .tokenUrl("https://oauth2.googleapis.com/token")
                                                        .scopes(scopes)
                                                )
                                        )
                        )
                );
    }
}
