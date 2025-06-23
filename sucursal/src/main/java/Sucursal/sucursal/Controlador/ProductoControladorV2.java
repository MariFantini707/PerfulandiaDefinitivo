package Sucursal.sucursal.Controlador;

import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Servicio.ProductoServicio;
import Sucursal.sucursal.Assemblers.ProductoModelAssembler;
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
@RequestMapping("/api/v2/productos")
public class ProductoControladorV2 {
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private ProductoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Producto>> getAllProductos() {
        List<EntityModel<Producto>> productos = productoServicio.getAllProductos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(productos,
            linkTo(methodOn(ProductoControladorV2.class).getAllProductos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Producto> getProductoById(@PathVariable Integer id) {
        Producto producto = productoServicio.getProductoById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return assembler.toModel(producto);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoServicio.createProducto(producto);
        return ResponseEntity
            .created(linkTo(methodOn(ProductoControladorV2.class).getProductoById(nuevoProducto.getIdProducto())).toUri())
            .body(assembler.toModel(nuevoProducto));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Producto>> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        Producto updated = productoServicio.updateProducto(id, producto);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id) {
        productoServicio.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
