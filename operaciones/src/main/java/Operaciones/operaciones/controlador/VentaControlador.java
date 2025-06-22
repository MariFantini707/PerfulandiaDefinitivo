package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.UsuarioDto;
import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.servicio.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ventas")
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas del sistema")
public class VentaControlador {

    @Autowired
    private VentaServicio ventaServicio;

    @Operation(summary = "Listar todas las ventas", description = "Devuelve todas las ventas registradas")
    @ApiResponse(responseCode = "200", description = "Ventas listadas correctamente")
    @GetMapping
    public List<Venta> getVentas() {
        return ventaServicio.getAllVentas();
    }

    @Operation(summary = "Obtener una venta por ID", description = "Devuelve los detalles de una venta específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idVenta\": 1, \"fechaVenta\": \"2025-06-21\", \"totalVenta\": 25000, \"idUsuario\": 3}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Venta no encontrada\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Optional<Venta> venta = ventaServicio.getVentaById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva venta", description = "Crea y guarda una nueva venta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para la venta",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos inválidos\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServicio.createVenta(venta));
    }

    @Operation(summary = "Actualizar una venta", description = "Modifica los datos de una venta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Venta no encontrada\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServicio.updateVenta(id, venta));
    }

    @Operation(summary = "Eliminar una venta", description = "Elimina una venta por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Venta no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Venta no encontrada\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id) {
        ventaServicio.deleteVenta(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtener el usuario asociado a una venta", description = "Devuelve los datos del usuario vinculado a la venta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"id\":3,\"nombreUsuario\":\"Carlos Gómez\",\"rutUsuario\":\"12345678-9\",\"correoUsuario\":\"carlos@ejemplo.com\",\"rolUsuario\":\"cliente\"}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Usuario no encontrado para esta venta\"}")
            )
        )
    })
    @GetMapping("/{id}/usuario")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDeVenta(@PathVariable Integer id) {
        try {
            UsuarioDto usuario = ventaServicio.obtenerUsuarioDeVenta(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
