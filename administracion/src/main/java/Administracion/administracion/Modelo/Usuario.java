package Administracion.administracion.Modelo;

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

    //esto se tiene que borrar, no sirve aqui -MARIAFANTINI
    public Object getRol() {
        throw new UnsupportedOperationException("Unimplemented method 'getRol'");
    }

    public void setRol(Object rol) {
        throw new UnsupportedOperationException("Unimplemented method 'setRol'");
    }

    public Object getCorreo_usuario() {
        throw new UnsupportedOperationException("Unimplemented method 'getCorreo_usuario'");
    }

    public void setCorreo_usuario(Object correo_usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'setCorreo_usuario'");
    }

    public Object getNombre_usuario() {
        throw new UnsupportedOperationException("Unimplemented method 'getNombre_usuario'");
    }

    public void setNombre_usuario(Object nombre_usuario) {
        throw new UnsupportedOperationException("Unimplemented method 'setNombre_usuario'");
    }


}
