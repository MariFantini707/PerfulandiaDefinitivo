package Sucursal.sucursal.Controlador;

import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Servicio.SucursalServicio;
import Sucursal.sucursal.Assemblers.SucursalModelAssembler;
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
@RequestMapping("/api/v2/sucursales")
public class SucursalControladorV2 {
    @Autowired
    private SucursalServicio sucursalServicio;
    @Autowired
    private SucursalModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Sucursal>> getAllSucursales() {
        List<EntityModel<Sucursal>> sucursales = sucursalServicio.getAllSucursales().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(sucursales,
            linkTo(methodOn(SucursalControladorV2.class).getAllSucursales()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Sucursal> getSucursalById(@PathVariable Integer id) {
        Sucursal sucursal = sucursalServicio.getSucursalById(id)
            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        return assembler.toModel(sucursal);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sucursal>> createSucursal(@RequestBody Sucursal sucursal) {
        Sucursal nuevaSucursal = sucursalServicio.createSucursal(sucursal);
        return ResponseEntity
            .created(linkTo(methodOn(SucursalControladorV2.class).getSucursalById(nuevaSucursal.getIdSucursal())).toUri())
            .body(assembler.toModel(nuevaSucursal));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sucursal>> updateSucursal(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
        Sucursal updated = sucursalServicio.updateSucursal(id, sucursal);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteSucursal(@PathVariable Integer id) {
        sucursalServicio.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
