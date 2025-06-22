package Soporte.soporte.Controlador;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Modelo.UsuarioDto;
import Soporte.soporte.Servicio.TicketSoporteServicio;

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
@RequestMapping("/api/v1/tickets")
@Tag(name = "Tickets de Soporte", description = "Operaciones relacionadas con los tickets del módulo de soporte")
public class TicketSoporteControlador {

    @Autowired
    private TicketSoporteServicio TicketSoporteServicio;

    @Operation(summary = "Listar todos los tickets de soporte", description = "Devuelve todos los tickets registrados en el sistema")
    @ApiResponse(responseCode = "200", description = "Tickets listados correctamente")
    @GetMapping
    public List<TicketSoporte> getTicketSoportes() {
        return TicketSoporteServicio.getAllTicketSoportes();
    }

    @Operation(summary = "Obtener un ticket por ID", description = "Retorna los detalles de un ticket específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"idTicket\":1,\"descripcionTicket\":\"No puedo ingresar al sistema\",\"estadoTicket\":\"Abierto\",\"idUsuario\":4}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Ticket no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TicketSoporte> getTicketSoporteById(@PathVariable Integer id) {
        Optional<TicketSoporte> ticketSoporte = TicketSoporteServicio.getTicketSoporteById(id);
        return ticketSoporte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener usuario asociado a un ticket", description = "Devuelve los datos del usuario que generó el ticket")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"id\":4,\"nombreUsuario\":\"María Pino\",\"correoUsuario\":\"maria@duocuc.cl\",\"rolUsuario\":\"cliente\"}")
            )
        ),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\": \"Usuario no encontrado\"}")
            )
        )
    })
    @GetMapping("/{id}/usuario")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDelTicket(@PathVariable Integer id) {
        UsuarioDto usuario = TicketSoporteServicio.obtenerUsuarioDelTicket(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Crear un nuevo ticket de soporte", description = "Registra un nuevo ticket en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos del ticket",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Datos inválidos\"}")
            )
        )
    })
    @PostMapping
    public ResponseEntity<TicketSoporte> createTicketSoporte(@RequestBody TicketSoporte ticketSoporte) {
        return ResponseEntity.ok(TicketSoporteServicio.createTicketSoporte(ticketSoporte));
    }

    @Operation(summary = "Actualizar un ticket de soporte", description = "Modifica los datos de un ticket existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Ticket no encontrado\"}")
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TicketSoporte> updateTicketSoporte(@PathVariable Integer id, @RequestBody TicketSoporte ticketSoporte) {
        return ResponseEntity.ok(TicketSoporteServicio.updateTicketSoporte(id, ticketSoporte));
    }

    @Operation(summary = "Eliminar un ticket de soporte", description = "Elimina un ticket por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ticket eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
            content = @Content(mediaType = "application/json",
                examples = @ExampleObject(value = "{\"error\":\"Ticket no encontrado\"}")
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicketSoporte(@PathVariable Integer id) {
        TicketSoporteServicio.deleteTicketSoporte(id);
        return ResponseEntity.ok().build();
    }
}
