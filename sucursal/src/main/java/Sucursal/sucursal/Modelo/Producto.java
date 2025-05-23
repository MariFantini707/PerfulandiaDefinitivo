package Sucursal.sucursal.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    
}
