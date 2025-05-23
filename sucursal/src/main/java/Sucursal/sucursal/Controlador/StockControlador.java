package Sucursal.sucursal.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import Sucursal.sucursal.Modelo.Stock;
import Sucursal.sucursal.Servicio.StockServicio;

@RestController
@RequestMapping("/stocks")
public class StockControlador {
    @Autowired
    private StockServicio stockServicio;

    @GetMapping
    public List<Stock> getStocks() {
        return stockServicio.getAllStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Integer id) {
        Optional<Stock> stock = stockServicio.getStockById(id);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockServicio.createStock(stock));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Integer id, @RequestBody Stock stock) {
        return ResponseEntity.ok(stockServicio.updateStock(id, stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Integer id) {
        stockServicio.deleteStock(id);
        return ResponseEntity.ok().build();
    }
}
