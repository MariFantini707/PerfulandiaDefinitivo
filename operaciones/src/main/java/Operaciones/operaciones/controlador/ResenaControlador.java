package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.servicio.ResenaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/resenas")
@Tag(name = "Reseñas", description = "Operaciones relacionadas con las reseñas de productos")
public class ResenaControlador {

    @Autowired
    private ResenaServicio resenaServicio;

    @Operation(summary = "Listar todas las reseñas", description = "Devuelve todas las reseñas registradas")
    @ApiResponse(responseCode = "200", description = "Reseñas listadas correctamente")
    @GetMapping
    public List<Resena> getResenas() {
        return resenaServicio.getAllResenas();
    }

    @Operation(summary = "Obtener una reseña por ID", description = "Devuelve los detalles de una reseña específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"idResena\": 1, \"comentarioResena\": \"Excelente atención\", \"puntuacionResena\": 5, \"idUsuario\": 7}"
                )
            )
        ),
        @ApiResponse(responseCode = "404", description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Reseña no encontrada\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Resena> getResenaById(@PathVariable Integer id) {
        Optional<Resena> resena = resenaServicio.getResenaById(id);
        return resena.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva reseña", description = "Registra una nueva reseña en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos de la reseña",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Datos inválidos\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<Resena> createResena(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaServicio.createResena(resena));
    }

    @Operation(summary = "Actualizar una reseña", description = "Modifica una reseña existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Reseña no encontrada\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(@PathVariable Integer id, @RequestBody Resena resena) {
        return ResponseEntity.ok(resenaServicio.updateResena(id, resena));
    }

    @Operation(summary = "Eliminar una reseña", description = "Elimina una reseña específica por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reseña eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Reseña no encontrada",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Reseña no encontrada\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResena(@PathVariable Integer id) {
        resenaServicio.deleteResena(id);
        return ResponseEntity.ok().build();
    }
}
