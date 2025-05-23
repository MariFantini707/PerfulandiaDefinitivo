package Logistica.logistica.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @Column(nullable = false)
    private LocalDate fechaPedido;

    @Column(nullable = false)
    private String estadoPedido;

    @Column(nullable = false)
    private Integer totalPedido;

    @Column(nullable = false)
    private Integer cantidadPedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Envio envio;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Proveedor proveedor;

    @Column(nullable = false)
    @JsonProperty("idUsuario")
    private Integer idUsuario;

    @Column(nullable = false)
    @JsonProperty("idProducto")
    private Integer idProducto;
    // @JsonProperty("idUsuario") es para que el nombre de la propiedad en el JSON sea "idUsuario"
    //comentario Maria: Para el Usuario que conecta con Pedido (gerente) opté por usar la misma lógica del JSON 
}
