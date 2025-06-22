package Sucursal.sucursal.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Servicio.SucursalServicio;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/v1/sucursales")
@Tag(name = "Sucursales", description = "Operaciones relacionadas con la gestión de sucursales")
public class SucursalControlador {

    @Autowired
    private SucursalServicio sucursalServicio;

    @Operation(summary = "Listar todas las sucursales", description = "Devuelve una lista de todas las sucursales registradas")
    @ApiResponse(responseCode = "200", description = "Sucursales obtenidas correctamente")
    @GetMapping
    public List<Sucursal> getSucursales() {
        return sucursalServicio.getAllSucursales();
    }

    @Operation(summary = "Obtener una sucursal por ID", description = "Retorna los detalles de una sucursal específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idSucursal\":1,\"nombreSucursal\":\"Sucursal Centro\",\"direccionSucursal\":\"Av. Libertador 123\",\"telefonoSucursal\":226789456}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Sucursal no encontrada\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Integer id) {
        Optional<Sucursal> sucursal = sucursalServicio.getSucursalById(id);
        return sucursal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva sucursal", description = "Registra una nueva sucursal en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Sucursal creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para la sucursal",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Datos inválidos\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalServicio.createSucursal(sucursal));
    }

    @Operation(summary = "Actualizar una sucursal", description = "Modifica los datos de una sucursal existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Sucursal no encontrada\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalServicio.updateSucursal(id, sucursal));
    }

    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Sucursal no encontrada\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSucursal(@PathVariable Integer id) {
        sucursalServicio.deleteSucursal(id);
        return ResponseEntity.ok().build();
    }
}
