package Soporte.soporte;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import Soporte.soporte.Modelo.TicketSoporte;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import Soporte.soporte.Servicio.TicketSoporteServicio;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@AutoConfigureMockMvc
class TicketSoporteControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TicketSoporteServicio ticketSoporteServicio;

    private TicketSoporte ticket;

    @BeforeEach
    void setUp() {
        ticket = new TicketSoporte();
        ticket.setIdTicket(1);
        ticket.setDescripcionTicket("No puedo ingresar al sistema");
        ticket.setEstadoTicket("Abierto");
        ticket.setFechaInicioTicket(LocalDate.of(2024, 1, 1));
        ticket.setFechaTerminoTicket(LocalDate.of(2024, 1, 2));
        ticket.setRespuestaTicket("Respuesta de prueba");
        ticket.setIdUsuario(1);
    }

    @Test
    void testGetTickets() throws Exception {
        mockMvc.perform(get("/api/v1/tickets"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetTicketById() throws Exception {
        mockMvc.perform(get("/api/v1/tickets/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostTicket() throws Exception {
        when(ticketSoporteServicio.createTicketSoporte(any(TicketSoporte.class))).thenReturn(ticket);
        mockMvc.perform(post("/api/v1/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket)))
            .andExpect(status().isOk());
    }
    @Test
    void testPutTicket() throws Exception {
        when(ticketSoporteServicio.getTicketSoporteById(1)).thenReturn(java.util.Optional.of(ticket));
        when(ticketSoporteServicio.updateTicketSoporte(eq(1), any(TicketSoporte.class))).thenReturn(ticket);
        mockMvc.perform(put("/api/v1/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket)))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteTicketSoporte() throws Exception {
        mockMvc.perform(delete("/api/v1/tickets/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
