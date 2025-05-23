package Administracion.administracion.Model;

import javax.management.relation.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuario")


public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUsuario;


    @Column(nullable=false, unique = false)
    private String NombreUsuario;

    @Column(unique=true, nullable=false)
    private String RutUsuario;

    @Column(nullable=false, unique = false)
    private String CorreoUsuario;

    @ManyToOne
    @JoinColumn(name = "IdRol", nullable = false)
    private Role RolUsuario;


}
