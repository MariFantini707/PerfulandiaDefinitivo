package Sucursal.sucursal.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Sucursal")
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSucursal;

    @Column(nullable=false, unique = false)
    private String NombreSucursal;

    @Column(unique=true, nullable=false)
    private String DireccionSucursal;

    @Column(nullable=false, unique = false)
    private Integer TelefonoSucursal;

}