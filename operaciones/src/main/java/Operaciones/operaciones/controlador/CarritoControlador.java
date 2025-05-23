package Operaciones.operaciones.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Operaciones.operaciones.modelo.Carrito;
import Operaciones.operaciones.servicio.CarritoServicio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/carritos")
public class CarritoControlador {
    @Autowired
    private CarritoServicio carritoServicio;

    @GetMapping
    public List<Carrito> getCarritos() {
        return carritoServicio.getAllCarritos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById(@RequestParam Integer id) {
        Carrito carrito = carritoServicio.getCarritoById(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createCarrito(@RequestBody Carrito entity) {
        carritoServicio.createCarrito(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Carrito creado con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        Carrito carritoActualizar = carritoServicio.updateCarrito(id, carrito);
        return ResponseEntity.ok().body(carritoActualizar.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVentas(@PathVariable Integer id) {
        Optional<Carrito> carrito = carritoServicio.getCarritoById(id);
        if (carrito.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        carritoServicio.deleteCarrito(id);
        return ResponseEntity.ok().build();
    }
    
    
}
