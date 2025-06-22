package Logistica.logistica.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un pedido logístico")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del pedido", example = "501")
    private Integer idPedido;

    @Column(nullable = false)
    @Schema(description = "Fecha en que se realizó el pedido", example = "2025-06-21")
    private LocalDate fechaPedido;

    @Column(nullable = false)
    @Schema(description = "Estado actual del pedido", example = "Pendiente")
    private String estadoPedido;

    @Column(nullable = false)
    @Schema(description = "Monto total del pedido", example = "150000")
    private Integer totalPedido;

    @Column(nullable = false)
    @Schema(description = "Cantidad total de productos en el pedido", example = "30")
    private Integer cantidadPedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Schema(description = "Envío asociado al pedido")
    private Envio envio;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Schema(description = "Proveedor que despacha el pedido")
    private Proveedor proveedor;

    @Column(nullable = false)
    @JsonProperty("idUsuario")
    @Schema(description = "ID del usuario que creó el pedido (gerente)", example = "3")
    private Integer idUsuario;

    @Column(nullable = false)
    @JsonProperty("idProducto")
    @Schema(description = "ID del producto solicitado", example = "45")
    private Integer idProducto;

    // @JsonProperty cambia el nombre de la propiedad en la salida JSON
    // Comentario de María: el Usuario (gerente) que conecta con Pedido se representa por idUsuario usando JSONProperty
}

