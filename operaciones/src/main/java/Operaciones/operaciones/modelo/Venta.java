package Operaciones.operaciones.modelo;

import lombok.Data;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idVenta;

    @Column(name="fechaVenta",nullable = true)
    private Date fechaVenta;
   
    @Column(name="totalVenta",nullable = false)
    private Integer totalVenta;
    
    @OneToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    private Carrito carrito;

    // Si es que supiera como se hace la relacion con el usuario:
    //@ManyToOne 
    //@JoinColumn(name = "idUsuario", nullable = false)
    //private Usuario usuario;   
    // Y como no sé:
    @Column(nullable = false)
    @JsonProperty("idUsuario")
    // @JsonProperty("idUsuario") es para que el nombre de la propiedad en el JSON sea "idUsuario"
    private Integer idUsuario;
}
