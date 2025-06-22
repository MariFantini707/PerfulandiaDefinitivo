package Sucursal.sucursal.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Servicio.ProductoServicio;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos de la sucursal")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Operation(summary = "Listar todos los productos", description = "Devuelve una lista de todos los productos registrados en la sucursal")
    @ApiResponse(responseCode = "200", description = "Productos obtenidos correctamente")
    @GetMapping
    public List<Producto> getProductos() {
        return productoServicio.getAllProductos();
    }

    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idProducto\":1,\"nombreProducto\":\"Notebook Lenovo\",\"precioProducto\":450000,\"categoriaProducto\":\"Tecnología\"}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Producto no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Optional<Producto> producto = productoServicio.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en la sucursal")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del producto",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos inválidos del producto\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServicio.createProducto(producto));
    }

    @Operation(summary = "Actualizar un producto", description = "Modifica los datos de un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Producto no encontrado\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoServicio.updateProducto(id, producto));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Producto no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        productoServicio.deleteProducto(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Aumentar stock de un producto", description = "Incrementa la cantidad de stock de un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Producto no encontrado\"}")
            )
        )
    })
    @PutMapping("/aumentarStock/{id}/{cantidad}")
    public ResponseEntity<Producto> aumentarStock(@PathVariable Integer id, @PathVariable Integer cantidad) {
        Producto producto = productoServicio.aumentarStock(id, cantidad);
        return ResponseEntity.ok(producto);
    }
}
