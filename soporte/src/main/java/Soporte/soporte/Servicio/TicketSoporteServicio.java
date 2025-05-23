package Soporte.soporte.Servicio;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Repositorio.TicketSoporteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketSoporteServicio {
    @Autowired
    private TicketSoporteRepositorio ticketSoporteRepositorio;

    public List<TicketSoporte> getAllTicketSoportes() {
        return ticketSoporteRepositorio.findAll();
    }

    public Optional<TicketSoporte> getTicketSoporteById(int id) {
        return ticketSoporteRepositorio.findById(id);
    }

    public TicketSoporte createTicketSoporte(TicketSoporte ticketSoporte) {
        return ticketSoporteRepositorio.save(ticketSoporte);
    }

    public TicketSoporte updateTicketSoporte(Integer id, TicketSoporte ticketSoporte) {
        if (ticketSoporteRepositorio.existsById(id)) {
            ticketSoporte.setIdTicket(id);
            return ticketSoporteRepositorio.save(ticketSoporte);
        }
        throw new RuntimeException("No se encontró la reseña");
    }

    public void deleteTicketSoporte(int id) {
        ticketSoporteRepositorio.deleteById(id);
    }


}
