package Administracion.administracion.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Administracion.administracion.Model.Usuario;
import Administracion.administracion.Repositorio.UsuarioRepositorio;
@Service

public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio UsuarioRepositorio;


    public List<Usuario> obtenerTodos() {
        return UsuarioRepositorio.findAll();
    }

    public Optional<Usuario> obtenerPorId(Integer id) {
        return UsuarioRepositorio.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        return UsuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> actualizar(Integer id, Usuario datos) {
        return UsuarioRepositorio.findById(id).map(p -> {
            p.setNombre_usuario(datos.getNombre_usuario());
            p.setCorreo_usuario(datos.getCorreo_usuario());
            p.setCorreo_usuario(datos.getCorreo_usuario());
            p.setRol(datos.getRol());
            return UsuarioRepositorio.save(p);
        });
    }

    public boolean eliminar(Integer id) {
        if (UsuarioRepositorio.existsById(id)) {
            UsuarioRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

}
