package Logistica.logistica.Controlador;

import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Servicio.EnvioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/envios")
@Tag(name = "Envios", description = "Operaciones relacionadas con el módulo de logística de envíos")
public class EnvioControlador {

    @Autowired
    private EnvioServicio envioServicio;

    @Operation(summary = "Listar todos los envíos", description = "Obtiene una lista de todos los envíos registrados")
    @ApiResponse(responseCode = "200", description = "Lista de envíos obtenida correctamente")
    @GetMapping
    public List<Envio> listarEnvios() {
        return envioServicio.listarEnvios();
    }

    @Operation(summary = "Obtener un envío por ID", description = "Retorna la información de un envío específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envío encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"idEnvio\":1,\"fechaEnvio\":\"2025-06-21\",\"estadoEnvio\":\"Entregado\",\"origen\":\"Santiago\"}"
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Envío no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Envío no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Integer id) {
        return envioServicio.obtenerEnvioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo envío", description = "Registra un nuevo envío en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envío creado exitosamente",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"idEnvio\":2,\"fechaEnvio\":\"2025-06-22\",\"estadoEnvio\":\"Pendiente\",\"origen\":\"Viña del Mar\"}"
                )
            )
        ),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos del envío inválidos\"}")
            )
        )
    })
    @PostMapping
    public Envio crearEnvio(@RequestBody Envio envio) {
        return envioServicio.guardarEnvio(envio);
    }

    @Operation(summary = "Actualizar un envío", description = "Modifica los datos de un envío existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envío actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Envío no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Envío no encontrado\"}")
            )
        )
    })
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

    @Operation(summary = "Eliminar un envío", description = "Elimina un envío por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Envío eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Envío no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Envío no encontrado\"}")
            )
        )
    })
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

