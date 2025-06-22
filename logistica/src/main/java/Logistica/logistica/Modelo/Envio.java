package Logistica.logistica.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un envío logístico")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del envío", example = "101")
    private Integer idEnvio;

    @Column(nullable = false)
    @Schema(description = "Fecha en que se realizó el envío", example = "2025-06-21")
    private LocalDate fechaEnvio;

    @Column(nullable = false)
    @Schema(description = "Estado actual del envío", example = "En tránsito")
    private String estadoEnvio;

    @Column(nullable = false)
    @Schema(description = "Origen del envío", example = "Santiago, Chile")
    private String origen;
}
