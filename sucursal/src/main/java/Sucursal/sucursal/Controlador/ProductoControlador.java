package Sucursal.sucursal.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Servicio.ProductoServicio;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoControlador {
    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> getProductos() {
        return productoServicio.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
        Optional<Producto> producto = productoServicio.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServicio.createProducto(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        return ResponseEntity.ok(productoServicio.updateProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        productoServicio.deleteProducto(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/aumentarStock/{id}/{cantidad}")
    public ResponseEntity<Producto> aumentarStock(@PathVariable Integer id, @PathVariable Integer cantidad) {
        Producto producto = productoServicio.aumentarStock(id, cantidad);
        return ResponseEntity.ok(producto);
    }
}
