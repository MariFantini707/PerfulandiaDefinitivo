package Soporte.soporte.Controlador;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Modelo.UsuarioDto;
import Soporte.soporte.Servicio.TicketSoporteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketSoporteControlador {
    @Autowired
    private TicketSoporteServicio TicketSoporteServicio;

    @GetMapping
    public List<TicketSoporte> getTicketSoportes() {
        return TicketSoporteServicio.getAllTicketSoportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketSoporte> getTicketSoporteById(@PathVariable Integer id) {
        Optional<TicketSoporte> ticketSoporte = TicketSoporteServicio.getTicketSoporteById(id);
        return ticketSoporte.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //este get conecta con el método aparte que escribí
    //se puede usar con: http://localhost:8084/tickets/{id}/usuario para ver la información del cliente que solicitó el ticket
    @GetMapping("/{id}/usuario")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDelTicket(@PathVariable Integer id) {
        UsuarioDto usuario = TicketSoporteServicio.obtenerUsuarioDelTicket(id);
        return ResponseEntity.ok(usuario);
}

    @PostMapping
    public ResponseEntity<TicketSoporte> createTicketSoporte(@RequestBody TicketSoporte ticketSoporte) {
        return ResponseEntity.ok(TicketSoporteServicio.createTicketSoporte(ticketSoporte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketSoporte> updateTicketSoporte(@PathVariable Integer id, @RequestBody TicketSoporte ticketSoporte) {
        return ResponseEntity.ok(TicketSoporteServicio.updateTicketSoporte(id, ticketSoporte));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicketSoporte(@PathVariable Integer id) {
        TicketSoporteServicio.deleteTicketSoporte(id);
        return ResponseEntity.ok().build();
    }

}
