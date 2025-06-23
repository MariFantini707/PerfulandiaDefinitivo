package Soporte.soporte;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Repositorio.TicketSoporteRepositorio;
import Soporte.soporte.Servicio.TicketSoporteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketSoporteServicioTest {
    @Mock
    private TicketSoporteRepositorio ticketSoporteRepositorio;
    @InjectMocks
    private TicketSoporteServicio ticketSoporteServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTicketSoportes() {
        TicketSoporte ticket = new TicketSoporte();
        when(ticketSoporteRepositorio.findAll()).thenReturn(Arrays.asList(ticket));
        List<TicketSoporte> result = ticketSoporteServicio.getAllTicketSoportes();
        assertEquals(1, result.size());
    }

    @Test
    void testGetTicketSoporteById() {
        TicketSoporte ticket = new TicketSoporte();
        when(ticketSoporteRepositorio.findById(1)).thenReturn(Optional.of(ticket));
        Optional<TicketSoporte> result = ticketSoporteServicio.getTicketSoporteById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateTicketSoporte() {
        TicketSoporte ticket = new TicketSoporte();
        when(ticketSoporteRepositorio.save(ticket)).thenReturn(ticket);
        TicketSoporte result = ticketSoporteServicio.createTicketSoporte(ticket);
        assertNotNull(result);
    }

    @Test
    void testDeleteTicketSoporte() {
        doNothing().when(ticketSoporteRepositorio).deleteById(1);
        ticketSoporteServicio.deleteTicketSoporte(1);
        verify(ticketSoporteRepositorio, times(1)).deleteById(1);
    }
}
