package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.servicio.CarritoServicio;
import Operaciones.operaciones.Assemblers.CarritoModelAssembler;
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
@RequestMapping("/api/v2/carritos")
public class CarritoControladorV2 {
    @Autowired
    private CarritoServicio carritoServicio;
    @Autowired
    private CarritoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Carrito>> getAllCarritos() {
        List<EntityModel<Carrito>> carritos = carritoServicio.getAllCarritos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(carritos,
            linkTo(methodOn(CarritoControladorV2.class).getAllCarritos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Carrito> getCarritoById(@PathVariable Integer id) {
        Carrito carrito = carritoServicio.getCarritosById(id)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
        return assembler.toModel(carrito);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> createCarrito(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoServicio.createCarrito(carrito);
        return ResponseEntity
            .created(linkTo(methodOn(CarritoControladorV2.class).getCarritoById(nuevoCarrito.getIdCarrito())).toUri())
            .body(assembler.toModel(nuevoCarrito));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Carrito>> updateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        Carrito updated = carritoServicio.updateCarritos(id, carrito);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCarrito(@PathVariable Integer id) {
        carritoServicio.deleteCarritos(id);
        return ResponseEntity.noContent().build();
    }
}
