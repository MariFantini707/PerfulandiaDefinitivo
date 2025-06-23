package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.servicio.VentaServicio;
import Operaciones.operaciones.Assemblers.VentaModelAssembler;
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
@RequestMapping("/api/v2/ventas")
public class VentaControladorV2 {
    @Autowired
    private VentaServicio ventaServicio;
    @Autowired
    private VentaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Venta>> getAllVentas() {
        List<EntityModel<Venta>> ventas = ventaServicio.getAllVentas().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(ventas,
            linkTo(methodOn(VentaControladorV2.class).getAllVentas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Venta> getVentaById(@PathVariable Integer id) {
        Venta venta = ventaServicio.getVentaById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return assembler.toModel(venta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> createVenta(@RequestBody Venta venta) {
        Venta nuevaVenta = ventaServicio.createVenta(venta);
        return ResponseEntity
            .created(linkTo(methodOn(VentaControladorV2.class).getVentaById(nuevaVenta.getIdVenta())).toUri())
            .body(assembler.toModel(nuevaVenta));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Venta>> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        Venta updated = ventaServicio.updateVenta(id, venta);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id) {
        ventaServicio.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
