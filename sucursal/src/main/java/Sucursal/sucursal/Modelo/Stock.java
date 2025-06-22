package Sucursal.sucursal.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stock")
@Data
@Schema(description = "Entidad que representa el stock disponible de un producto")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del registro de stock", example = "3001")
    private Integer idStock;

    @Column(name = "cantidad", nullable = false)
    @Schema(description = "Cantidad disponible del producto en stock", example = "25")
    private Integer cantidad;

    @Column(name = "idProducto", nullable = false)
    @Schema(description = "ID del producto asociado al stock", example = "101")
    private Integer idProducto;

}

