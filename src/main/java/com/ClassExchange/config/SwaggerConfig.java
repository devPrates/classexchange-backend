package com.ClassExchange.config;

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
                        .title("ClassExchange API")
                        .version("1.0.0")
                        .description("API para gerenciamento de campus e cursos do sistema ClassExchange")
                        .contact(new Contact()
                                .name("Gabriel Prates Bitencourt")
                                .email("devprates@gmail.com")
                        )
                );
    }
}
