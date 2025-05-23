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
    private Integer idUsuario;


    @Column(nullable=false, unique = false)
    private String NombreUsuario;

    @Column(unique=true, nullable=false)
    private String RutUsuario;

    @Column(nullable=false, unique = false)
    private String CorreoUsuario;

    @ManyToOne
    @JoinColumn(name = "IdRol", nullable = false)
    private Role RolUsuario;

    public Object getidUsuario() {
        throw new UnsupportedOperationException("Unimplemented method 'getidUsuario'");
    }

    public void setidUsuario(Object getidUsuario) {
        throw new UnsupportedOperationException("Unimplemented method 'setidUsuario'");
    }

    public Object getnombreUsuario() {
        throw new UnsupportedOperationException("Unimplemented method 'getnombreUsuario'");
    }

    public void setnombreUsuario(Object getnombreUsuario) {
        throw new UnsupportedOperationException("Unimplemented method 'setnombreUsuario'");
    }

    public Object getrutUsuario() {
        throw new UnsupportedOperationException("Unimplemented method 'getrutUsuario'");
    }

    public void setrutUsuario(Object getrutUsuario) {
        throw new UnsupportedOperationException("Unimplemented method 'setrutUsuario'");
    }

    public Object getcorreoUsuario() {
        throw new UnsupportedOperationException("Unimplemented method 'getcorreoUsuario'");
    }

    public void setcorreoUsuario(Object getcorreoUsuario) {
        throw new UnsupportedOperationException("Unimplemented method 'setcorreoUsuario'");
    }

    public Object getRol() {
        throw new UnsupportedOperationException("Unimplemented method 'getRol'");
    }

    public void setRol(Object rol) {
        throw new UnsupportedOperationException("Unimplemented method 'setRol'");
    }



}
