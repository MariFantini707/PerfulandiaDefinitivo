package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.UsuarioDto;
import Operaciones.operaciones.modelo.Venta;
import Operaciones.operaciones.servicio.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaControlador {
    @Autowired
    private VentaServicio ventaServicio;

    @GetMapping
    public List<Venta> getVentas() {
        return ventaServicio.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Integer id) {
        Optional<Venta> venta = ventaServicio.getVentaById(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServicio.createVenta(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServicio.updateVenta(id, venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Integer id) {
        ventaServicio.deleteVenta(id);
        return ResponseEntity.ok().build();
    }

    // este metodo ayuda a obtener el usuario de una venta
    @GetMapping("/{id}/usuario")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDeVenta(@PathVariable Integer id) {
        try {
            UsuarioDto usuario = ventaServicio.obtenerUsuarioDeVenta(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}