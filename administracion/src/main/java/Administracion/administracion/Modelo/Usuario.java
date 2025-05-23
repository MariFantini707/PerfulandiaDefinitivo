package Administracion.administracion.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name="nombreUsuario",nullable=false, unique = false)
    private String nombreUsuario;

    @Column(name="rutUsuario",unique=true, nullable=false)
    private String rutUsuario;

    @Column(name="correoUsuario",nullable=false, unique = false)
    private String correoUsuario;

    @Column(name="rolUsuario",nullable=false, unique = false)
    private String rolUsuario;

}
