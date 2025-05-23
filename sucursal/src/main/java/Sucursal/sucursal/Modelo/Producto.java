package Sucursal.sucursal.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(name = "nombreProducto", nullable = false, length = 100)
    private String nombreProducto;

    @Column(name = "precioProducto", nullable = false)
    private Integer precioProducto;

    @Column(name = "categoriaProducto", nullable = false, length = 50)
    private String categoriaProducto;

    @ManyToOne
    @JoinColumn(name = "idSucursal", nullable = false)
    private Sucursal sucursal;
    @Column(name = "idCarrito", nullable = false)
    private Integer idCarrito;
    @ManyToOne
    @JoinColumn(name = "idStock", nullable = false)
    private Stock stock;



}
