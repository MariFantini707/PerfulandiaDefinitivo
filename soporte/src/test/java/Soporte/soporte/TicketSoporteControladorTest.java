package Soporte.soporte;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TicketSoporteControladorTest {
    @Autowired
    private MockMvc mockMvc;

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
        String json = "{\"descripcionTicket\":\"Test\",\"estadoTicket\":\"Abierto\",\"fechaInicioTicket\":\"2024-01-01\",\"fechaTerminoTicket\":\"2024-01-02\",\"respuestaTicket\":\"Respuesta\",\"idUsuario\":1}";
        mockMvc.perform(post("/api/v1/tickets").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testPutTicket() throws Exception {
        String json = "{\"descripcionTicket\":\"Test2\",\"estadoTicket\":\"Cerrado\",\"fechaInicioTicket\":\"2024-01-03\",\"fechaTerminoTicket\":\"2024-01-04\",\"respuestaTicket\":\"Respuesta2\",\"idUsuario\":2}";
        mockMvc.perform(put("/api/v1/tickets/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteTicketSoporte() throws Exception {
        mockMvc.perform(delete("/api/v1/tickets/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
