package Sucursal.sucursal.Controlador;

import Sucursal.sucursal.Modelo.Stock;
import Sucursal.sucursal.Servicio.StockServicio;
import Sucursal.sucursal.Assemblers.StockModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/stocks")
public class StockControladorV2 {
    @Autowired
    private StockServicio stockServicio;
    @Autowired
    private StockModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Stock>> getAllStocks() {
        List<EntityModel<Stock>> stocks = stockServicio.getAllStocks().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(stocks,
            linkTo(methodOn(StockControladorV2.class).getAllStocks()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Stock> getStockById(@PathVariable Integer id) {
        Stock stock = stockServicio.getStockById(id)
            .orElseThrow(() -> new RuntimeException("Stock no encontrado"));
        return assembler.toModel(stock);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Stock>> createStock(@RequestBody Stock stock) {
        Stock nuevoStock = stockServicio.createStock(stock);
        return ResponseEntity
            .created(linkTo(methodOn(StockControladorV2.class).getStockById(nuevoStock.getIdStock())).toUri())
            .body(assembler.toModel(nuevoStock));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Stock>> updateStock(@PathVariable Integer id, @RequestBody Stock stock) {
        Stock updated = stockServicio.updateStock(id, stock);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteStock(@PathVariable Integer id) {
        stockServicio.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
