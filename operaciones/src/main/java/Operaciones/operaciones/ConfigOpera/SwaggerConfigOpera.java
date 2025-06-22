package Operaciones.operaciones.ConfigOpera;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigOpera {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2025 - Operaciones")
                        .version("1.0")
                        .description("documentacion de la API Operaciones para el sistema de perfulandia"));
    }
}
