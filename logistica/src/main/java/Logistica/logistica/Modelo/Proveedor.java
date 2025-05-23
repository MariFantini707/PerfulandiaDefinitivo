package Logistica.logistica.Modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;

    @Column(length = 150, nullable = false)
    private String nombreProveedor;

    @Column(nullable = false)
    private Integer telefonoProveedor;

    @Column(length = 100, nullable = false)
    private String correoProveedor;

    @Column(length = 150, nullable = false)
    private String direccionProveedor;
}
