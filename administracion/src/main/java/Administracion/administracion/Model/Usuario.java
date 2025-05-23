package main.java.Administracion.administracion.Model;

import javax.annotation.processing.Generated;
import javax.management.relation.Role;

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
    @JoinColumn(name = "id_rol", nullable = false)
    private Role rol;

}
