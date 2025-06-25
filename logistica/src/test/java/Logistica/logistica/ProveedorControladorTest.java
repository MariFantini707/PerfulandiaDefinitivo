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
class ProveedorControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProveedores() throws Exception {
        mockMvc.perform(get("/api/v1/proveedores"))
            .andExpect(status().isOk());
    }
    @Test
    void testGetProveedorById() throws Exception {
        mockMvc.perform(get("/api/v1/proveedores/1"))
            .andExpect(status().isOk());
    }
    @Test
    void testPostProveedor() throws Exception {
        String json = "{\"nombreProveedor\":\"Test\",\"telefonoProveedor\":1234567,\"correoProveedor\":\"test@test.com\",\"direccionProveedor\":\"Calle 123\"}";
        mockMvc.perform(post("/api/v1/proveedores").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testPutProveedor() throws Exception {
        String json = "{\"nombreProveedor\":\"Test2\",\"telefonoProveedor\":7654321,\"correoProveedor\":\"test2@test.com\",\"direccionProveedor\":\"Calle 456\"}";
        mockMvc.perform(put("/api/v1/proveedores/1").contentType("application/json").content(json))
            .andExpect(status().isOk());
    }
    @Test
    void testDeleteProveedor() throws Exception {
        mockMvc.perform(delete("/api/v1/proveedores/1"))
            .andExpect(status().isNoContent()); // 204
    }
}
