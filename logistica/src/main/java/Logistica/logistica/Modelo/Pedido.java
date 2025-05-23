package Logistica.logistica.Modelo;

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

    //@ManyToOne pero creo que no se usa cuando es una entidad de otra api 
    //@JoinColumn(nullable = false, name = idUsuario)
    //private Integer idUsuario;
}
