package Logistica.logistica.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO que representa un usuario del sistema")
public class UsuarioDto {

    @JsonProperty("id")
    @Schema(description = "Identificador único del usuario", example = "1")
    private Integer id;

    @JsonProperty("nombreUsuario")
    @Schema(description = "Nombre completo del usuario", example = "Carla Soto")
    private String nombreUsuario;

    @JsonProperty("rutUsuario")
    @Schema(description = "RUT del usuario", example = "12345678-5")
    private String rutUsuario;

    @JsonProperty("correoUsuario")
    @Schema(description = "Correo electrónico del usuario", example = "carla.soto@duocuc.cl")
    private String correoUsuario;

    @JsonProperty("rolUsuario")
    @Schema(description = "Rol asignado al usuario dentro del sistema", example = "gerente")
    private String rolUsuario;
}

