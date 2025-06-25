package Logistica.logistica;

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
class EnvioControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEnvios() throws Exception {
        mockMvc.perform(get("/api/v1/envios"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetEnvioById() throws Exception {
        mockMvc.perform(get("/api/v1/envios/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostEnvio() throws Exception {
        String json = "{\"fechaEnvio\":\"2025-06-21\",\"estadoEnvio\":\"Pendiente\",\"origen\":\"Santiago\"}";
        mockMvc.perform(post("/api/v1/envios").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testPutEnvio() throws Exception {
        String json = "{\"fechaEnvio\":\"2025-06-22\",\"estadoEnvio\":\"Entregado\",\"origen\":\"Viña del Mar\"}";
        mockMvc.perform(put("/api/v1/envios/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteEnvio() throws Exception {
        mockMvc.perform(delete("/api/v1/envios/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
