package Soporte.soporte.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO que representa a un usuario dentro del módulo de soporte")
public class UsuarioDto {

    @JsonProperty("id")
    @Schema(description = "Identificador único del usuario", example = "12")
    private Integer id;

    @JsonProperty("nombreUsuario")
    @Schema(description = "Nombre completo del usuario", example = "Alejandro Rojas")
    private String nombreUsuario;

    @JsonProperty("rutUsuario")
    @Schema(description = "RUT del usuario", example = "12345678-9")
    private String rutUsuario;

    @JsonProperty("correoUsuario")
    @Schema(description = "Correo electrónico del usuario", example = "alejandro.rojas@duocuc.cl")
    private String correoUsuario;

    @JsonProperty("rolUsuario")
    @Schema(description = "Rol del usuario en el sistema", example = "soporte")
    private String rolUsuario;
}
