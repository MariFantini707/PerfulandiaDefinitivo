package Sucursal.sucursal.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
@Schema(description = "Entidad que representa un producto asociado a una sucursal")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del producto", example = "101")
    private Integer idProducto;

    @Column(name = "nombreProducto", nullable = false, length = 100)
    @Schema(description = "Nombre del producto", example = "Teclado mecánico RGB")
    private String nombreProducto;

    @Column(name = "precioProducto", nullable = false)
    @Schema(description = "Precio del producto en CLP", example = "35990")
    private Integer precioProducto;

    @Column(name = "categoriaProducto", nullable = false, length = 50)
    @Schema(description = "Categoría a la que pertenece el producto", example = "Periféricos")
    private String categoriaProducto;

    @ManyToOne
    @JoinColumn(name = "idSucursal", nullable = false)
    @Schema(description = "Sucursal a la que pertenece el producto")
    private Sucursal sucursal;

    @Column(name = "idCarrito", nullable = false)
    @Schema(description = "ID del carrito donde fue agregado el producto", example = "2001")
    private Integer idCarrito;

    @ManyToOne
    @JoinColumn(name = "idStock", nullable = false)
    @Schema(description = "Stock asociado al producto")
    private Stock stock;
}
