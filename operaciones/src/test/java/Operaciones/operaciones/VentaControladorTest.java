package Operaciones.operaciones;

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
class VentaControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetVentas() throws Exception {
        mockMvc.perform(get("/api/v1/ventas"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetVentaById() throws Exception {
        mockMvc.perform(get("/api/v1/ventas/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostVenta() throws Exception {
        String json = "{\"fechaVenta\":\"2024-01-01\",\"totalVenta\":1000,\"carrito\":null,\"idUsuario\":1}";
        mockMvc.perform(post("/api/v1/ventas").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testPutVenta() throws Exception {
        String json = "{\"fechaVenta\":\"2024-01-02\",\"totalVenta\":2000,\"carrito\":null,\"idUsuario\":2}";
        mockMvc.perform(put("/api/v1/ventas/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteVenta() throws Exception {
        mockMvc.perform(delete("/api/v1/ventas/1"))
            .andExpect(status().isOk());
    }
}
