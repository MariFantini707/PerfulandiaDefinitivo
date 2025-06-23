package Soporte.soporte.Controlador;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Servicio.TicketSoporteServicio;
import Soporte.soporte.Assemblers.TicketSoporteModelAssembler;
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
@RequestMapping("/api/v2/tickets")
public class TicketSoporteControladorV2 {
    @Autowired
    private TicketSoporteServicio ticketSoporteServicio;
    @Autowired
    private TicketSoporteModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<TicketSoporte>> getAllTicketSoportes() {
        List<EntityModel<TicketSoporte>> tickets = ticketSoporteServicio.getAllTicketSoportes().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(tickets,
            linkTo(methodOn(TicketSoporteControladorV2.class).getAllTicketSoportes()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<TicketSoporte> getTicketSoporteById(@PathVariable Integer id) {
        TicketSoporte ticket = ticketSoporteServicio.getTicketSoporteById(id)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        return assembler.toModel(ticket);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TicketSoporte>> createTicketSoporte(@RequestBody TicketSoporte ticket) {
        TicketSoporte nuevoTicket = ticketSoporteServicio.createTicketSoporte(ticket);
        return ResponseEntity
            .created(linkTo(methodOn(TicketSoporteControladorV2.class).getTicketSoporteById(nuevoTicket.getIdTicket())).toUri())
            .body(assembler.toModel(nuevoTicket));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TicketSoporte>> updateTicketSoporte(@PathVariable Integer id, @RequestBody TicketSoporte ticket) {
        TicketSoporte updated = ticketSoporteServicio.updateTicketSoporte(id, ticket);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteTicketSoporte(@PathVariable Integer id) {
        ticketSoporteServicio.deleteTicketSoporte(id);
        return ResponseEntity.noContent().build();
    }
}
