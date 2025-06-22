package Logistica.logistica.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Servicio.EnvioServicio;

@RestController
@RequestMapping("/api/v1/envios")
public class EnvioControlador {

    @Autowired
    private EnvioServicio envioServicio;

    @GetMapping
    public List<Envio> listarEnvios() {
        return envioServicio.listarEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Integer id) {
        return envioServicio.obtenerEnvioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Envio crearEnvio(@RequestBody Envio envio) {
        return envioServicio.guardarEnvio(envio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        if (envioServicio.obtenerEnvioPorId(id).isPresent()) {
            envio.setIdEnvio(id);
            Envio actualizado = envioServicio.actualizarEnvio(envio);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEnvio(@PathVariable Integer id) {
        if (envioServicio.obtenerEnvioPorId(id).isPresent()) {
            envioServicio.eliminarEnvio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
