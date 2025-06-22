package Administracion.administracion.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un usuario del sistema")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Integer idUsuario;

    @Column(name = "nombreUsuario", nullable = false)
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String nombreUsuario;

    @Column(name = "rutUsuario", unique = true, nullable = false)
    @Schema(description = "RUT del usuario, único en el sistema", example = "12345678-9")
    private String rutUsuario;

    @Column(name = "correoUsuario", nullable = false)
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@duocuc.cl")
    private String correoUsuario;

    @Column(name = "rolUsuario", nullable = false)
    @Schema(description = "Rol que cumple el usuario en el sistema", example = "soporte")
    private String rolUsuario;
}
