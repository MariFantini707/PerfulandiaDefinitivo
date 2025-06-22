package Soporte.soporte.ConfigSoporte;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigSoporte {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2025 - Soporte")
                        .version("1.0")
                        .description("documentacion de la API Soporte para el sistema de perfulandia"));
    }
}