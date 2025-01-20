package com.walrus.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI walrusOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Walrus API")
                        .description("Walrus 后端API文档")
                        .version("1.0"));
    }
} 