package Soporte.soporte.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket_soporte")
@Schema(description = "Entidad que representa un ticket de soporte ingresado por un usuario")
public class TicketSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    @Schema(description = "Identificador único del ticket", example = "102")
    private Integer idTicket;

    @Column(name = "descripcion_ticket", nullable = false, length = 500)
    @Schema(description = "Descripción detallada del problema reportado", example = "La aplicación no permite iniciar sesión con mis credenciales.")
    private String descripcionTicket;

    @Column(name = "estado_ticket", nullable = false, length = 50)
    @Schema(description = "Estado actual del ticket", example = "Abierto")
    private String estadoTicket;

    @Column(name = "fecha_inicio_ticket", nullable = false)
    @Schema(description = "Fecha en la que se creó el ticket", example = "2025-06-21")
    private LocalDate fechaInicioTicket;

    @Column(name = "fecha_termino_ticket")
    @Schema(description = "Fecha en la que se resolvió o cerró el ticket", example = "2025-06-23")
    private LocalDate fechaTerminoTicket;

    @Column(name = "respuesta_ticket", nullable = false, length = 500)
    @Schema(description = "Respuesta o solución entregada por el equipo de soporte", example = "Se restableció la contraseña y se verificó el acceso.")
    private String respuestaTicket;

    @Column(name = "id_usuario", nullable = false)
    @JsonProperty("idUsuario")
    @Schema(description = "ID del usuario que creó el ticket o que responde a este (dependiendo de su id)", example = "12")
    private Integer idUsuario;

}

