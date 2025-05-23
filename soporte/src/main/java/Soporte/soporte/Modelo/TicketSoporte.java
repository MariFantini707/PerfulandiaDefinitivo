package Soporte.soporte.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket_soporte")
public class TicketSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "descripcion_ticket", nullable = false, length = 500)
    private String descripcionTicket;

    @Column(name = "estado_ticket", nullable = false, length = 50)
    private String estadoTicket;

    @Column(name = "fecha_inicio_ticket", nullable = false)
    private LocalDate fechaInicioTicket;

    @Column(name = "fecha_termino_ticket")
    private LocalDate fechaTerminoTicket;

    @Column(name = "respuesta_ticket", nullable = false, length = 500)
    private String respuestaTicket;

    @Column(name = "id_usuario", nullable = false)
    @JsonProperty("idUsuario")
    private Integer idUsuario;
}
