package Operaciones.operaciones.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "resena")
@Schema(description = "Entidad que representa una reseña realizada por un usuario sobre un producto o servicio")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la reseña", example = "501")
    private Integer idResena;

    @Column(name = "comentarioResena", nullable = true)
    @Schema(description = "Comentario textual opcional de la reseña", example = "Muy buen servicio, recomendable")
    private String comentarioResena;

    @Column(name = "puntuacionResena", nullable = false)
    @Schema(description = "Puntuación asignada por el usuario, de 1 a 5", example = "4")
    private Integer puntuacionResena;

    // Opción sin relación directa
    @Column(name = "idUsuario", nullable = false)
    @Schema(description = "ID del usuario que escribió la reseña", example = "7")
    private Integer idUsuario;


}
