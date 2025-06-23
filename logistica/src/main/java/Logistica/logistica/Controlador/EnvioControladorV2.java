package Logistica.logistica.Controlador;

import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Servicio.EnvioServicio;
import Logistica.logistica.Assemblers.EnvioModelAssembler;
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
@RequestMapping("/api/v2/envios")
public class EnvioControladorV2 {
    @Autowired
    private EnvioServicio envioServicio;
    @Autowired
    private EnvioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioServicio.listarEnvios().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(envios,
            linkTo(methodOn(EnvioControladorV2.class).getAllEnvios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Envio> getEnvioById(@PathVariable Integer id) {
        Envio envio = envioServicio.obtenerEnvioPorId(id)
            .orElseThrow(() -> new RuntimeException("Envio no encontrado"));
        return assembler.toModel(envio);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> createEnvio(@RequestBody Envio envio) {
        Envio nuevoEnvio = envioServicio.guardarEnvio(envio);
        return ResponseEntity
            .created(linkTo(methodOn(EnvioControladorV2.class).getEnvioById(nuevoEnvio.getIdEnvio())).toUri())
            .body(assembler.toModel(nuevoEnvio));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> updateEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
        envio.setIdEnvio(id);
        Envio updated = envioServicio.actualizarEnvio(envio);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEnvio(@PathVariable Integer id) {
        envioServicio.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }
}
