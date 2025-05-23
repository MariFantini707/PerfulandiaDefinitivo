package Sucursal.sucursal.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStock;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "idProducto", nullable = false)
    private Integer idProducto;

}
