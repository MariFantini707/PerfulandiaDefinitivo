package Sucursal.sucursal.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Servicio.SucursalServicio;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalControlador {
    @Autowired
    private SucursalServicio sucursalServicio;

    @GetMapping
    public List<Sucursal> getSucursales() {
        return sucursalServicio.getAllSucursales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Integer id) {
        Optional<Sucursal> sucursal = sucursalServicio.getSucursalById(id);
        return sucursal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalServicio.createSucursal(sucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalServicio.updateSucursal(id, sucursal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSucursal(@PathVariable Integer id) {
        sucursalServicio.deleteSucursal(id);
        return ResponseEntity.ok().build();
    }
}
