package Operaciones.operaciones.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "carrito")
@Schema(description = "Entidad que representa un carrito de compras")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del carrito", example = "1001")
    private Integer idCarrito;

    @Column(name = "cantidadCarrito", nullable = false)
    @Schema(description = "Cantidad total de productos en el carrito", example = "3")
    private Integer cantidadCarrito;
}
