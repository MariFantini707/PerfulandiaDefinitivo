package Sucursal.sucursal.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Sucursal")
@Data
@Schema(description = "Entidad que representa una sucursal física de la empresa")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la sucursal", example = "1")
    private Integer idSucursal;

    @Column(nullable = false)
    @Schema(description = "Nombre de la sucursal", example = "Sucursal Plaza Oeste")
    private String nombreSucursal;

    @Column(unique = true, nullable = false)
    @Schema(description = "Dirección física de la sucursal", example = "Av. Central 1234, Maipú, Santiago")
    private String direccionSucursal;

    @Column(nullable = false)
    @Schema(description = "Número de teléfono de contacto de la sucursal", example = "227654321")
    private Integer telefonoSucursal;
}
