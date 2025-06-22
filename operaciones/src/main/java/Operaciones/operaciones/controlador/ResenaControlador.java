package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.servicio.ResenaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaControlador {
    @Autowired
    private ResenaServicio resenaServicio;

    @GetMapping
    public List<Resena> getResenas() {
        return resenaServicio.getAllResenas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> getResenaById(@PathVariable Integer id) {
        Optional<Resena> resena = resenaServicio.getResenaById(id);
        return resena.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resena> createResena(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaServicio.createResena(resena));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(@PathVariable Integer id, @RequestBody Resena resena) {
        return ResponseEntity.ok(resenaServicio.updateResena(id, resena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResena(@PathVariable Integer id) {
        resenaServicio.deleteResena(id);
        return ResponseEntity.ok().build();
    }
}