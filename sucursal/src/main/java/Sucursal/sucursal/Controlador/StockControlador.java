package Sucursal.sucursal.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Sucursal.sucursal.Modelo.Stock;
import Sucursal.sucursal.Servicio.StockServicio;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stocks")
@Tag(name = "Stock", description = "Operaciones relacionadas con la gestión de stock de productos")
public class StockControlador {

    @Autowired
    private StockServicio stockServicio;

    @Operation(summary = "Listar todos los registros de stock", description = "Devuelve una lista con todos los registros de stock")
    @ApiResponse(responseCode = "200", description = "Stocks obtenidos correctamente")
    @GetMapping
    public List<Stock> getStocks() {
        return stockServicio.getAllStocks();
    }

    @Operation(summary = "Obtener stock por ID", description = "Devuelve un registro de stock específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idStock\":1,\"cantidad\":50,\"idProducto\":10}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Stock no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Stock no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Integer id) {
        Optional<Stock> stock = stockServicio.getStockById(id);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo registro de stock", description = "Registra un nuevo stock para un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Stock creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para el stock",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Datos inválidos\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockServicio.createStock(stock));
    }

    @Operation(summary = "Actualizar stock existente", description = "Modifica la cantidad o producto asociado a un stock")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Stock no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Stock no encontrado\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Integer id, @RequestBody Stock stock) {
        return ResponseEntity.ok(stockServicio.updateStock(id, stock));
    }

    @Operation(summary = "Eliminar registro de stock", description = "Elimina un registro de stock específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Stock eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Stock no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Stock no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Integer id) {
        stockServicio.deleteStock(id);
        return ResponseEntity.ok().build();
    }
}

