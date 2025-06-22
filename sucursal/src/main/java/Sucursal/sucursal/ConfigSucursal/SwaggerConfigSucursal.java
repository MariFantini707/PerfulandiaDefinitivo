package Sucursal.sucursal.ConfigSucursal;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigSucursal {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 2025 - Sucursal")
                        .version("1.0")
                        .description("documentacion de la API Sucursal para el sistema de perfulandia"));
    }
}
