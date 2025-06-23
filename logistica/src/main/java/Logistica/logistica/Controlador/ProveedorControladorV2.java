package Logistica.logistica.Controlador;

import Logistica.logistica.Modelo.Proveedor;
import Logistica.logistica.Servicio.ProveedorServicio;
import Logistica.logistica.Assemblers.ProveedorModelAssembler;
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
@RequestMapping("/api/v2/proveedores")
public class ProveedorControladorV2 {
    @Autowired
    private ProveedorServicio proveedorServicio;
    @Autowired
    private ProveedorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Proveedor>> getAllProveedores() {
        List<EntityModel<Proveedor>> proveedores = proveedorServicio.listarProveedores().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(proveedores,
            linkTo(methodOn(ProveedorControladorV2.class).getAllProveedores()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Proveedor> getProveedorById(@PathVariable Integer id) {
        Proveedor proveedor = proveedorServicio.obtenerProveedorPorId(id)
            .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        return assembler.toModel(proveedor);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Proveedor>> createProveedor(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorServicio.guardarProveedor(proveedor);
        return ResponseEntity
            .created(linkTo(methodOn(ProveedorControladorV2.class).getProveedorById(nuevoProveedor.getIdProveedor())).toUri())
            .body(assembler.toModel(nuevoProveedor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Proveedor>> updateProveedor(@PathVariable Integer id, @RequestBody Proveedor proveedor) {
        proveedor.setIdProveedor(id);
        Proveedor updated = proveedorServicio.actualizarProveedor(proveedor);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProveedor(@PathVariable Integer id) {
        proveedorServicio.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
