package Soporte.soporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;
import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Repositorio.TicketSoporteRepositorio;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private TicketSoporteRepositorio ticketSoporteRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            TicketSoporte ticket = new TicketSoporte();
            ticket.setDescripcionTicket(faker.lorem().paragraph());
            ticket.setEstadoTicket(faker.options().option("Abierto", "Cerrado", "En Proceso"));
            // Fechas sin métodos deprecated
            LocalDate fechaInicio = LocalDate.now().minusDays(faker.number().numberBetween(1, 1000));
            LocalDate fechaTermino = fechaInicio.plusDays(faker.number().numberBetween(1, 30));
            ticket.setFechaInicioTicket(fechaInicio);
            ticket.setFechaTerminoTicket(fechaTermino);
            ticket.setRespuestaTicket(faker.lorem().sentence());
            ticket.setIdUsuario(faker.number().numberBetween(1, 10));
            ticketSoporteRepositorio.save(ticket);
        }
    }
}
