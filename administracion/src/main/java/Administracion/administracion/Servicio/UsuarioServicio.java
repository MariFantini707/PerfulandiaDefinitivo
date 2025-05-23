package Administracion.administracion.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Administracion.administracion.Modelo.Usuario;
import Administracion.administracion.Repositorio.UsuarioRepositorio;
@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> findById(Integer id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> actualizar(Integer id, Usuario datos) {
        Optional<Usuario> p = usuarioRepositorio.findById(id);
        if (p.isPresent()) {
            Usuario usuario = p.get();
            usuario.setNombreUsuario(datos.getNombreUsuario());
            usuario.setRutUsuario(datos.getRutUsuario());
            usuario.setCorreoUsuario(datos.getCorreoUsuario());
            usuario.setRolUsuario(datos.getRolUsuario());
            usuarioRepositorio.save(usuario);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    public boolean delete(Integer id) {
        if (usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

}
