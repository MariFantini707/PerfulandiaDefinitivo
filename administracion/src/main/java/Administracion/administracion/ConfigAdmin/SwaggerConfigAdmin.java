package Administracion.administracion.ConfigAdmin;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigAdmin {
    @Bean
        public OpenAPI customOpenAPI(){
            return new OpenAPI()
                    .info(new Info()
                            .title("API 2025 - Administracion")
                            .version("1.0")
                            .description("documentacion de la API administración para el sistema de perfulandia"));
        }

}
