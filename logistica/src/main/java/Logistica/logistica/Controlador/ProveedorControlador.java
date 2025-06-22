package Logistica.logistica.Controlador;

import Logistica.logistica.Modelo.Proveedor;
import Logistica.logistica.Servicio.ProveedorServicio;
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

@RestController
@RequestMapping("/api/v1/proveedores")
@Tag(name = "Proveedores", description = "Operaciones relacionadas con proveedores logísticos")
public class ProveedorControlador {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @Operation(summary = "Listar todos los proveedores", description = "Devuelve una lista con todos los proveedores registrados")
    @ApiResponse(responseCode = "200", description = "Proveedores listados exitosamente")
    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorServicio.listarProveedores();
    }

    @Operation(summary = "Obtener un proveedor por ID", description = "Retorna los datos de un proveedor específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"idProveedor\": 1, \"nombreProveedor\": \"Tech Global\", \"telefonoProveedor\": 987654321, \"correoProveedor\": \"contacto@techglobal.cl\", \"direccionProveedor\": \"Av. Providencia 1234\"}"
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Proveedor no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedor(@PathVariable Integer id) {
        return proveedorServicio.obtenerProveedorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo proveedor", description = "Registra un nuevo proveedor en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del proveedor",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos del proveedor inválidos\"}")
            )
        )
    })
    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorServicio.guardarProveedor(proveedor);
    }

    @Operation(summary = "Actualizar un proveedor", description = "Modifica los datos de un proveedor ya existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Proveedor no encontrado\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        if (proveedorServicio.obtenerProveedorPorId(id).isPresent()) {
            proveedor.setIdProveedor(id);
            Proveedor proveedorActualizado = proveedorServicio.actualizarProveedor(proveedor);
            return ResponseEntity.ok(proveedorActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un proveedor", description = "Elimina un proveedor específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Proveedor eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Proveedor no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Integer id) {
        if (proveedorServicio.obtenerProveedorPorId(id).isPresent()) {
            proveedorServicio.eliminarProveedor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
