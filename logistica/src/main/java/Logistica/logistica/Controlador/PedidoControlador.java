package Logistica.logistica.Controlador;

import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Modelo.ProductoDto;
import Logistica.logistica.Servicio.PedidoServicio;
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
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con pedidos logísticos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @Operation(summary = "Listar todos los pedidos", description = "Devuelve todos los pedidos registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoServicio.listarPedidos();
    }

    @Operation(summary = "Obtener un pedido por ID", description = "Devuelve los detalles de un pedido por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idPedido\": 1, \"estadoPedido\": \"Pendiente\", \"cantidadPedido\": 3, \"idUsuario\": 4, \"idProducto\": 7}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Pedido no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Integer id) {
        return pedidoServicio.obtenerPedidoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Registra un nuevo pedido en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos del pedido inválidos\"}")
            )
        )
    })
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoServicio.guardarPedido(pedido);
    }

    @Operation(summary = "Actualizar un pedido existente", description = "Modifica los datos de un pedido usando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Pedido no encontrado\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        if (pedidoServicio.obtenerPedidoPorId(id).isPresent()) {
            pedido.setIdPedido(id);
            Pedido actualizado = pedidoServicio.actualizarPedido(pedido);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un pedido", description = "Elimina un pedido específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Pedido no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer id) {
        if (pedidoServicio.obtenerPedidoPorId(id).isPresent()) {
            pedidoServicio.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener el producto asociado a un pedido", description = "Devuelve los datos del producto vinculado a un pedido específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto asociado encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idProducto\": 7, \"nombreProducto\": \"Monitor LED\", \"precioProducto\": 85000, \"categoriaProducto\": \"Pantallas\"}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Producto o pedido no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Producto no encontrado para el pedido\"}")
            )
        )
    })
    @GetMapping("/{id}/producto")
    public ResponseEntity<ProductoDto> obtenerProductoDePedido(@PathVariable Integer id) {
        try {
            ProductoDto producto = pedidoServicio.obtenerProductoDePedido(id);
            return ResponseEntity.ok(producto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
