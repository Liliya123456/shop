package com.liliya.shop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("SpringShop API")
                                .description("Spring boot sample application for internet shop")
                                .version("v0.0.1")
                                .license(new License()
                                        .name("By Liliya")
                                        .url("https://github.com/Liliya123456/shop")
                                )
                );
    }
}
