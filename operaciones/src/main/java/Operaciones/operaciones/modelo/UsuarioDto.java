package Operaciones.operaciones.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO que representa un usuario en operaciones")
public class UsuarioDto {

    @Schema(description = "Identificador único del usuario", example = "1")
    private Integer id;

    @Schema(description = "Nombre completo del usuario", example = "Fernanda López")
    private String nombreUsuario;

    @Schema(description = "RUT del usuario", example = "12345678-9")
    private String rutUsuario;

    @Schema(description = "Correo electrónico del usuario", example = "fernanda.lopez@duocuc.cl")
    private String correoUsuario;

    @Schema(description = "Rol del usuario dentro del sistema", example = "cliente")
    private String rolUsuario;
}

