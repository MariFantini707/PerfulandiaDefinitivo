package Operaciones.operaciones.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.servicio.CarritoServicio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/v1/carritos")
@Tag(name = "Carritos", description = "Operaciones relacionadas con los carritos de compra")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    @Operation(summary = "Listar todos los carritos", description = "Obtiene la lista de todos los carritos")
    @ApiResponse(responseCode = "200", description = "Carritos listados correctamente")
    @GetMapping
    public List<Carrito> getCarritos() {
        return carritoServicio.getAllCarritos();
    }

    @Operation(summary = "Obtener un carrito por ID", description = "Devuelve un carrito específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idCarrito\":1,\"cantidadCarrito\":3}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Carrito no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Carrito>> getCarritoById(@RequestParam Integer id) {
        Optional<Carrito> carrito = carritoServicio.getCarritosById(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear un nuevo carrito", description = "Registra un nuevo carrito de compras en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Carrito creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para el carrito",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos inválidos\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<String> createCarrito(@RequestBody Carrito entity) {
        carritoServicio.createCarrito(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carrito creado con éxito");
    }

    @Operation(summary = "Actualizar un carrito", description = "Actualiza los datos de un carrito existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Carrito no encontrado\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCarritos(@PathVariable Integer id, @RequestBody Carrito carrito) {
        Carrito carritoActualizar = carritoServicio.updateCarritos(id, carrito);
        return ResponseEntity.ok().body(carritoActualizar.toString());
    }

    @Operation(summary = "Eliminar un carrito", description = "Elimina un carrito por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Carrito eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Carrito no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Carrito no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVentas(@PathVariable Integer id) {
        Optional<Carrito> carrito = carritoServicio.getCarritosById(id);
        if (carrito.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        carritoServicio.deleteCarritos(id);
        return ResponseEntity.ok().build();
    }
}
