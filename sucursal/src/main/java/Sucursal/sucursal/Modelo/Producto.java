package Sucursal.sucursal.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
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

    // Getters y setters
    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public Integer getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(Integer precioProducto) { this.precioProducto = precioProducto; }
    public String getCategoriaProducto() { return categoriaProducto; }
    public void setCategoriaProducto(String categoriaProducto) { this.categoriaProducto = categoriaProducto; }
}
