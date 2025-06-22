package Administracion.administracion.Controlador;

import Administracion.administracion.Modelo.Usuario;
import Administracion.administracion.Servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuarios", description = "Operaciones CRUD relacionadas con los usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Operation(summary = "Listar todos los usuarios", description = "Devuelve una lista con todos los usuarios del sistema")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @GetMapping
    public List<Usuario> listarusuarioes() {
        return usuarioServicio.findAll();
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve los datos de un usuario a partir de su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"idUsuario\":1,\"nombreUsuario\":\"Juan Pérez\",\"rutUsuario\":\"12345678-9\",\"correoUsuario\":\"juan@correo.com\",\"rolUsuario\":\"admin\"}"
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Usuario no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerusuario(@PathVariable Integer id) {
        return usuarioServicio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Registra un nuevo usuario en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos de entrada inválidos\"}")
            )
        )
    })
    @PostMapping
    public Usuario crearusuario(@RequestBody Usuario usuario) {
        return usuarioServicio.save(usuario);
    }

    @Operation(summary = "Actualizar un usuario", description = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Usuario no encontrado con ID: X\"}")
            )
        ),
        @ApiResponse(responseCode = "500", description = "Error en la actualización",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Error al actualizar el usuario\"}")
            )
        )
    })
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

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Usuario no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarusuario(@PathVariable Integer id) {
        if (usuarioServicio.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

