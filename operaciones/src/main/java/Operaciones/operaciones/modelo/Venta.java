package Operaciones.operaciones.modelo;

import lombok.Data;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import Operaciones.operaciones.servicio.UsuarioServicio;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    //@ManyToOne 
    //@JoinColumn(name = "idUsuario", nullable = false)
    //UsuarioDto usuario;   
    // Y como no sé:
    @Column(name="idUsuario",nullable = false)
    private Integer idUsuario;
}
