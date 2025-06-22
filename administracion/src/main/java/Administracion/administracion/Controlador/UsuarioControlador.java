package Administracion.administracion.Controlador;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Administracion.administracion.Modelo.Usuario;
import Administracion.administracion.Servicio.UsuarioServicio;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listarusuarioes() {
        return usuarioServicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerusuario(@PathVariable Integer id) {
        return usuarioServicio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crearusuario(@RequestBody Usuario usuario) {
        return usuarioServicio.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarusuario(@PathVariable Integer id, @RequestBody Usuario datos) {
        try {
            Optional<Usuario> usuarioActualizado = usuarioServicio.actualizar(id, datos);
        
            if (usuarioActualizado.isPresent()) {
                return ResponseEntity.ok(usuarioActualizado.get());
            } else {
                return ResponseEntity.status(404).body("usuario no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar el usuario: " + e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarusuario(@PathVariable Integer id) {
        if (usuarioServicio.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
