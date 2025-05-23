package Soporte.soporte.Servicio;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Modelo.UsuarioDto;
import Soporte.soporte.Repositorio.TicketSoporteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class TicketSoporteServicio {
    @Autowired
    private TicketSoporteRepositorio ticketSoporteRepositorio;

    private final RestTemplate restTemplate = new RestTemplate();

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

    //Metodo aparte
    public UsuarioDto obtenerUsuarioDelTicket(Integer idTicket) {
        TicketSoporte ticket = ticketSoporteRepositorio.findById(idTicket)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        String url = "http://localhost:8081/usuario/" + ticket.getIdUsuario();
        return restTemplate.getForObject(url, UsuarioDto.class);
    }

    //Este método va a realizar una consulta a otro microservicio (Administración) para obtener los datos del usuario asociado a un ticket de soporte, basándose en el idUsuario guardado
    //recuerda usar bien el localhost...


}
