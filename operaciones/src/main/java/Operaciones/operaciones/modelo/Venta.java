package Operaciones.operaciones.modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "venta")
@Schema(description = "Entidad que representa una venta realizada en el sistema")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la venta", example = "1001")
    private Integer idVenta;

    @Column(name = "fechaVenta", nullable = true)
    @Schema(description = "Fecha en la que se realizó la venta", example = "2025-06-21")
    private Date fechaVenta;

    @Column(name = "totalVenta", nullable = false)
    @Schema(description = "Monto total de la venta", example = "45990")
    private Integer totalVenta;

    @OneToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    @Schema(description = "Carrito asociado a la venta")
    private Carrito carrito;

    @Column(name = "idUsuario", nullable = false)
    @Schema(description = "ID del usuario que realizó la compra", example = "3")
    private Integer idUsuario;


}

