package Logistica.logistica;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Servicio.EnvioServicio;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.time.LocalDate;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EnvioServicio envioServicio;

    private Envio envio;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        envio = new Envio();
        envio.setIdEnvio(1);
        envio.setFechaEnvio(LocalDate.of(2025, 6, 21));
        envio.setEstadoEnvio("Pendiente");
        envio.setOrigen("Santiago");
    }

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
        when(envioServicio.guardarEnvio(any(Envio.class))).thenReturn(envio);
        mockMvc.perform(post("/api/v1/envios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
            .andExpect(status().isOk());
    }
    @Test
    void testPutEnvio() throws Exception {
        when(envioServicio.obtenerEnvioPorId(1)).thenReturn(java.util.Optional.of(envio));
        when(envioServicio.actualizarEnvio(any(Envio.class))).thenReturn(envio);
        mockMvc.perform(put("/api/v1/envios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteEnvio() throws Exception {
        mockMvc.perform(delete("/api/v1/envios/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
